package moofarm.ssuckssuck.domain.user.presentation.dto.response;

import moofarm.ssuckssuck.domain.avatar.domain.vo.AvatarInfoVO;
import moofarm.ssuckssuck.domain.oauth.domain.OauthServerType;
import moofarm.ssuckssuck.global.common.MainCategory;
import moofarm.ssuckssuck.global.common.SubCategory;
import moofarm.ssuckssuck.domain.user.domain.vo.UserInfoVO;

public record SignUpResponse (
        String email,
        String name,
        String nickname,
        OauthServerType oauthServerType,
        MainCategory mainCategory,
        SubCategory subCategory,
        AvatarInfoVO avatarInfoVO,
        Boolean isFirst
) {
    public SignUpResponse(UserInfoVO userInfoVO, Boolean isFirst) {
        this(userInfoVO.email(), userInfoVO.name(), userInfoVO.nickname(), userInfoVO.oauthServerType(),
                userInfoVO.mainCategory(), userInfoVO.subCategory(), userInfoVO.avatarInfoVO(), isFirst);
    }
}
