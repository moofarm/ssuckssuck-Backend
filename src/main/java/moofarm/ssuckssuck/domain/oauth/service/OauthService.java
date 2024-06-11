package moofarm.ssuckssuck.domain.oauth.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moofarm.ssuckssuck.domain.credential.domain.repository.RefreshTokenRedisEntityRepository;
import moofarm.ssuckssuck.domain.credential.service.CredentialServiceUtils;
import moofarm.ssuckssuck.domain.oauth.authcode.AuthCodeRequestUrlProviderComposite;
import moofarm.ssuckssuck.domain.oauth.client.OauthMemberClientComposite;
import moofarm.ssuckssuck.domain.oauth.domain.OauthMember;
import moofarm.ssuckssuck.domain.oauth.domain.OauthServerType;
import moofarm.ssuckssuck.domain.oauth.domain.repository.OauthMemberRepository;
import moofarm.ssuckssuck.domain.oauth.presentation.dto.request.OauthLoginRequest;
import moofarm.ssuckssuck.domain.oauth.presentation.dto.response.OauthLoginResponse;
import moofarm.ssuckssuck.domain.user.domain.User;
import moofarm.ssuckssuck.domain.user.domain.repository.UserRepository;
import moofarm.ssuckssuck.global.security.JwtTokenProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OauthService {

    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
    private final OauthMemberClientComposite oauthMemberClientComposite;
    private final OauthMemberRepository oauthMemberRepository;
    private final UserRepository userRepository;
    private final RefreshTokenRedisEntityRepository refreshTokenRedisEntityRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final CredentialServiceUtils credentialServiceUtils;

    public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
        return authCodeRequestUrlProviderComposite.provide(oauthServerType);
    }

    @Transactional
    public OauthLoginResponse login(OauthLoginRequest oauthLoginRequest, HttpServletResponse response) {
        OauthMember oauthMember = oauthMemberClientComposite.fetch(oauthLoginRequest.oauthServerType(), oauthLoginRequest.code());

        OauthMember savedMember = oauthMemberRepository.findByOauthServerTypeAndEmail(oauthMember.getOauthServerType(), oauthMember.getEmail())
                .orElseGet(() -> oauthMemberRepository.save(oauthMember));

        Optional<User> optionalUser = userRepository.findByOauthServerTypeAndEmail(savedMember.getOauthServerType(), savedMember.getEmail());

        if (!optionalUser.isPresent()) {
            return new OauthLoginResponse(savedMember.getOauthMemberInfo(), true);
        }

        User user = optionalUser.get();
        processUserTokens(user, response);

        return new OauthLoginResponse(savedMember.getOauthMemberInfo(), false);
    }

    private void processUserTokens(User user, HttpServletResponse response) {
        String accessToken = jwtTokenProvider.generateAccessToken(user.getId());

        String refreshToken = credentialServiceUtils.generateRefreshToken(user.getId());

        jwtTokenProvider.setHeaderAccessToken(response, accessToken);
        jwtTokenProvider.setHeaderRefreshToken(response, refreshToken);
    }
}
