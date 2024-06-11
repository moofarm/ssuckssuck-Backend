package moofarm.ssuckssuck.domain.oauth.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import moofarm.ssuckssuck.domain.oauth.domain.OauthServerType;
import moofarm.ssuckssuck.domain.oauth.presentation.dto.request.OauthLoginRequest;
import moofarm.ssuckssuck.domain.oauth.presentation.dto.response.OauthLoginResponse;
import moofarm.ssuckssuck.domain.oauth.service.OauthService;
import org.hibernate.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

@Tag(name = "인증", description = "로그인 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class OauthController {

    private final OauthService oauthService;

    @SecurityRequirements
    @Operation(summary = "소셜 로그인")
    @PostMapping("/user/login")
    public OauthLoginResponse login(
            @RequestBody OauthLoginRequest oauthLoginRequest,
            HttpServletResponse response
    ) {
        return oauthService.login(oauthLoginRequest, response);
    }
}
