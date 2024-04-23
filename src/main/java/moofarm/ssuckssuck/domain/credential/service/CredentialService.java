package moofarm.ssuckssuck.domain.credential.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import moofarm.ssuckssuck.domain.credential.domain.RefreshTokenRedisEntity;
import moofarm.ssuckssuck.domain.credential.domain.repository.RefreshTokenRedisEntityRepository;
import moofarm.ssuckssuck.global.exception.InvalidTokenException;
import moofarm.ssuckssuck.global.exception.RefreshTokenExpiredException;
import moofarm.ssuckssuck.global.security.JwtTokenProvider;
import moofarm.ssuckssuck.global.utils.user.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class CredentialService implements CredentialServiceUtils {

    private static final Logger log = LoggerFactory.getLogger(CredentialService.class);
    private final RefreshTokenRedisEntityRepository refreshTokenRedisEntityRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserUtils userUtils;

    // refreshToken 저장
//    @Transactional
//    public


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
    public void tokenRefresh(String requestRefreshToken, HttpServletResponse response) {
        RefreshTokenRedisEntity refreshTokenRedisEntity = refreshTokenRedisEntityRepository.findByRefreshToken(requestRefreshToken)
                .orElseThrow(() -> RefreshTokenExpiredException.EXCEPTION);

//        Optional<RefreshTokenRedisEntity> byRefreshToken = refreshTokenRedisEntityRepository.findByRefreshToken(requestRefreshToken);
//        log.info(byRefreshToken.orElse(null).toString());

//        RefreshTokenRedisEntity refreshTokenRedisEntity = byRefreshToken.get();
        Long userId = jwtTokenProvider.parseRefreshToken(requestRefreshToken);

        if (!userId.toString().equals(refreshTokenRedisEntity.getId())) {
            throw InvalidTokenException.EXCEPTION;
        }

        String accessToken = jwtTokenProvider.generateAccessToken(userId);
        String refreshToken = generateRefreshToken(userId);

        jwtTokenProvider.setHeaderRefreshToken(response, refreshToken);
        jwtTokenProvider.setHeaderAccessToken(response, accessToken);
    }
}
