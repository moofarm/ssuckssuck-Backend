package moofarm.ssuckssuck.domain.user.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moofarm.ssuckssuck.domain.avatar.domain.Avatar;
import moofarm.ssuckssuck.domain.avatar.service.AvatarServiceUtils;
import moofarm.ssuckssuck.domain.oauth.domain.OauthMember;
import moofarm.ssuckssuck.domain.oauth.domain.repository.OauthMemberRepository;
import moofarm.ssuckssuck.domain.user.exception.NicknameDuplicationException;
import moofarm.ssuckssuck.global.common.MainCategory;
import moofarm.ssuckssuck.global.common.SubCategory;
import moofarm.ssuckssuck.domain.user.domain.User;
import moofarm.ssuckssuck.domain.user.domain.repository.UserRepository;
import moofarm.ssuckssuck.global.exception.InconsistencyCategoryException;
import moofarm.ssuckssuck.domain.user.presentation.dto.request.SignupRequest;
import moofarm.ssuckssuck.domain.user.presentation.dto.request.UpdateCategoryRequest;
import moofarm.ssuckssuck.domain.user.presentation.dto.response.NicknameDuplicationResponse;
import moofarm.ssuckssuck.domain.user.presentation.dto.response.SignUpResponse;
import moofarm.ssuckssuck.domain.user.presentation.dto.response.UserProfileResponse;
import moofarm.ssuckssuck.global.exception.UserNotFoundException;
import moofarm.ssuckssuck.global.security.JwtTokenProvider;
import moofarm.ssuckssuck.global.utils.user.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static moofarm.ssuckssuck.global.common.MainCategory.ETC;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final OauthMemberRepository oauthMemberRepository;
    private final UserUtils userUtils;
    private final AvatarServiceUtils avatarServiceUtils;
    private final JwtTokenProvider jwtTokenProvider;

    // 회원가입
    @Transactional
    public SignUpResponse signUp(SignupRequest signupRequest, HttpServletResponse response) {
        if (userRepository.existsByNickname(signupRequest.nickname())) {
            throw NicknameDuplicationException.EXCEPTION;
        }
        Avatar avatar = avatarServiceUtils.createAvatar();

        User user = User.createUser(
                signupRequest.name(),
                signupRequest.email(),
                signupRequest.nickname(),
                signupRequest.oauthServerType(),
                signupRequest.mainCategory(),
                signupRequest.subCategory(),
                avatar
        );

        userRepository.save(user);

        String accessToken = jwtTokenProvider.generateAccessToken(user.getId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getId());

        jwtTokenProvider.setHeaderAccessToken(response, accessToken);
        jwtTokenProvider.setHeaderRefreshToken(response, refreshToken);

        return new SignUpResponse(user.getUserInfo(), true);
    }

    // 회원 정보 조회
    public UserProfileResponse getUserProfile() {
        User user = userUtils.getUserFromSecurityContext();

        return new UserProfileResponse(user.getUserInfo());
    }

    // 관심사 수정
    @Transactional
    public UserProfileResponse updateCategory(UpdateCategoryRequest updateCategoryRequest) {
        User user = userUtils.getUserFromSecurityContext();

        if (!isValidCategoryUpdate(updateCategoryRequest)) {
            throw InconsistencyCategoryException.EXCEPTION;
        }

        user.updateCategory(updateCategoryRequest.mainCategory(), updateCategoryRequest.subCategory());

        return new UserProfileResponse(user.getUserInfo());
    }

    // 회원 탈퇴
    @Transactional
    public void userWithdraw() {
        User user = userUtils.getUserFromSecurityContext();

        OauthMember oauthMember = oauthMemberRepository
                .findByOauthServerTypeAndEmail(user.getOauthServerType(), user.getEmail())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        userRepository.delete(user);
        oauthMemberRepository.delete(oauthMember);
    }

    // 닉네임 중복 확인
    public NicknameDuplicationResponse isNicknameDuplicate(String nickname) {
        boolean check = userRepository.existsByNickname(nickname);

        return new NicknameDuplicationResponse(check);
    }

    // 카테고리 업데이트 유효성 검사
    private boolean isValidCategoryUpdate(UpdateCategoryRequest updateCategoryRequest) {
        return updateCategoryRequest.mainCategory() == ETC ||
                validateCategory(updateCategoryRequest.mainCategory(), updateCategoryRequest.subCategory());
    }

    // 카테고리 분류 확인
    public boolean validateCategory(MainCategory mainCategory, SubCategory subCategory) {
        return subCategory.isPartOf(mainCategory);
    }
}
