package moofarm.ssuckssuck.domain.credential.service;

import jakarta.servlet.http.HttpServletResponse;
import moofarm.ssuckssuck.domain.credential.presentation.dto.request.TokenRefreshRequest;

public interface CredentialServiceUtils {
    String generateRefreshToken(Long userId);
    void tokenRefresh(TokenRefreshRequest tokenRefreshRequest, HttpServletResponse response);
}
