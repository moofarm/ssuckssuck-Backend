package moofarm.ssuckssuck.domain.credential.service;

import jakarta.servlet.http.HttpServletResponse;

public interface CredentialServiceUtils {
    String generateRefreshToken(Long userId);
    void tokenRefresh(String requestRefreshToken, HttpServletResponse response);
}
