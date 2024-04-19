package moofarm.ssuckssuck.domain.avatar.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import moofarm.ssuckssuck.domain.avatar.presentation.dto.request.AddExperienceRequest;
import moofarm.ssuckssuck.domain.avatar.presentation.dto.response.AddExperienceResponse;
import moofarm.ssuckssuck.domain.avatar.service.AvatarService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "아바타", description = "아바타 관련 API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AvatarController {

    private final AvatarService avatarService;

    @Operation(summary = "경험치 증가")
    @PostMapping("/up")
    public AddExperienceResponse signUp() {
        return avatarService.addExperience();
    }
}
