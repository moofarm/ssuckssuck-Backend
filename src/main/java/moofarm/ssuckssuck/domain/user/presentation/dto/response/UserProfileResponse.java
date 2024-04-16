package moofarm.ssuckssuck.domain.user.presentation.dto.response;

import moofarm.ssuckssuck.domain.character.domain.vo.AvatarInfoVO;
import moofarm.ssuckssuck.domain.oauth.domain.OauthServerType;
import moofarm.ssuckssuck.global.common.MainCategory;
import moofarm.ssuckssuck.global.common.SubCategory;
import moofarm.ssuckssuck.domain.user.domain.vo.UserInfoVO;

public record UserProfileResponse(
        String email,
        String name,
        String nickname,
        OauthServerType oauthServerType,
        MainCategory mainCategory,
        SubCategory subCategory
) {
    public UserProfileResponse(UserInfoVO userInfoVO) {
        this(userInfoVO.email(), userInfoVO.name(), userInfoVO.nickname(), userInfoVO.oauthServerType(),
                userInfoVO.mainCategory(), userInfoVO.subCategory());
        SubCategory subCategory,
        AvatarInfoVO avatarInfoVO
) {
    public UserProfileResponse(UserInfoVO userInfoVO) {
        this(userInfoVO.email(), userInfoVO.name(), userInfoVO.nickname(), userInfoVO.oauthServerType(),
                userInfoVO.mainCategory(), userInfoVO.subCategory(), userInfoVO.avatarInfoVO());
    }
}
