package moofarm.ssuckssuck.domain.credential.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import moofarm.ssuckssuck.domain.credential.domain.RefreshTokenRedisEntity;
import moofarm.ssuckssuck.domain.credential.domain.repository.RefreshTokenRedisEntityRepository;
import moofarm.ssuckssuck.domain.credential.presentation.dto.request.TokenRefreshRequest;
import moofarm.ssuckssuck.global.exception.InvalidTokenException;
import moofarm.ssuckssuck.global.exception.RefreshTokenExpiredException;
import moofarm.ssuckssuck.global.security.JwtTokenProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
@Transactional
public class CredentialService implements CredentialServiceUtils {

    private final RefreshTokenRedisEntityRepository refreshTokenRedisEntityRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public String generateRefreshToken(Long userId) {
        refreshTokenRedisEntityRepository.findById(userId.toString()).ifPresent(refreshTokenRedisEntity -> {
            refreshTokenRedisEntityRepository.delete(refreshTokenRedisEntity);
        });

        String refreshToken = jwtTokenProvider.generateRefreshToken(userId);
        Long tokenExpiredAt = jwtTokenProvider.getRefreshTokenTTlSecond();

        RefreshTokenRedisEntity build = RefreshTokenRedisEntity.builder()
                .id(userId.toString())
                .refreshToken(refreshToken)
                .ttl(tokenExpiredAt)
                .build();

        refreshTokenRedisEntityRepository.save(build);

        return refreshToken;
    }

    @Override
    public void tokenRefresh(TokenRefreshRequest tokenRefreshRequest, HttpServletResponse response) {
        RefreshTokenRedisEntity refreshTokenRedisEntity = refreshTokenRedisEntityRepository.findByRefreshToken(tokenRefreshRequest.refreshToken())
                .orElseThrow(() -> RefreshTokenExpiredException.EXCEPTION);

        Long userId = jwtTokenProvider.parseRefreshToken(tokenRefreshRequest.refreshToken());

        if (!userId.toString().equals(refreshTokenRedisEntity.getId())) {
            throw InvalidTokenException.EXCEPTION;
        }

        String accessToken = jwtTokenProvider.generateAccessToken(userId);
        String refreshToken = generateRefreshToken(userId);

        jwtTokenProvider.setHeaderRefreshToken(response, refreshToken);
        jwtTokenProvider.setHeaderAccessToken(response, accessToken);
    }
}
