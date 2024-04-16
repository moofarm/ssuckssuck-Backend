package moofarm.ssuckssuck.domain.character.presentation.dto.response;

import moofarm.ssuckssuck.domain.character.domain.vo.AvatarInfoVO;
import moofarm.ssuckssuck.domain.oauth.domain.OauthServerType;
import moofarm.ssuckssuck.domain.user.domain.vo.UserInfoVO;
import moofarm.ssuckssuck.global.common.MainCategory;
import moofarm.ssuckssuck.global.common.SubCategory;

public record AddExperienceResponse(
        String email,
        String name,
        String nickname,
        OauthServerType oauthServerType,
        MainCategory mainCategory,
        SubCategory subCategory,
        AvatarInfoVO avatarInfoVO
) {
    public AddExperienceResponse(UserInfoVO userInfoVO) {
        this(userInfoVO.email(), userInfoVO.name(), userInfoVO.nickname(), userInfoVO.oauthServerType(),
                userInfoVO.mainCategory(), userInfoVO.subCategory(), userInfoVO.avatarInfoVO());
    }
}
