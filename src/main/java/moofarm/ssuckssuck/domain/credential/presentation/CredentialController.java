package moofarm.ssuckssuck.domain.credential.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import moofarm.ssuckssuck.domain.credential.presentation.dto.request.TokenRefreshRequest;
import moofarm.ssuckssuck.domain.credential.service.CredentialService;
import org.springframework.web.bind.annotation.*;

@Tag(name = "TokenRefresh", description = "token refresh 관련 API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CredentialController {

    private final CredentialService credentialService;

    @Operation(summary = "토큰 refresh")
    @PostMapping("/refresh")
    public void tokenRefresh(@RequestBody TokenRefreshRequest tokenRefreshRequest, HttpServletResponse response) {
        credentialService.tokenRefresh(tokenRefreshRequest, response);
    }
}
