package moofarm.ssuckssuck.domain.user.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import moofarm.ssuckssuck.domain.user.presentation.dto.request.SignupRequest;
import moofarm.ssuckssuck.domain.user.presentation.dto.request.UpdateCategoryRequest;
import moofarm.ssuckssuck.domain.user.presentation.dto.response.NicknameDuplicationResponse;
import moofarm.ssuckssuck.domain.user.presentation.dto.response.SignUpResponse;
import moofarm.ssuckssuck.domain.user.presentation.dto.response.UserProfileResponse;
import moofarm.ssuckssuck.domain.user.service.UserService;
import org.springframework.web.bind.annotation.*;

@Tag(name = "유저", description = "유저 관련 API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @SecurityRequirements
    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    public SignUpResponse signUp(@RequestBody @Valid SignupRequest signupRequest, HttpServletResponse response) {
        return userService.signUp(signupRequest, response);
    }

    @Operation(summary = "회원 정보 조회")
    @GetMapping("/")
    public UserProfileResponse getUserProfile() {
        return userService.getUserProfile();
    }

    @Operation(summary = "관심사 수정")
    @PatchMapping("/")
    public UserProfileResponse updateCategory(@RequestBody @Valid UpdateCategoryRequest updateCategoryRequest) {
        return userService.updateCategory(updateCategoryRequest);
    }

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping
    public void userWithdraw() {
        userService.userWithdraw();
    }

    @Operation(summary = "닉네임 중복 확인")
    @GetMapping("/nick/{nickname}")
    public NicknameDuplicationResponse nicknameDuplication(@PathVariable String nickname) {
        return userService.isNicknameDuplicate(nickname);
    }
}
