package moofarm.ssuckssuck.domain.user.domain.vo;

import moofarm.ssuckssuck.domain.character.domain.Avatar;
import moofarm.ssuckssuck.domain.character.domain.vo.AvatarInfoVO;
import moofarm.ssuckssuck.domain.oauth.domain.OauthServerType;
import moofarm.ssuckssuck.global.common.MainCategory;
import moofarm.ssuckssuck.global.common.SubCategory;

public record UserInfoVO(
        Long id,
        String name,
        String email,
        String nickname,
        OauthServerType oauthServerType,
        MainCategory mainCategory,
        SubCategory subCategory,
        AvatarInfoVO avatarInfoVO
) {
}
