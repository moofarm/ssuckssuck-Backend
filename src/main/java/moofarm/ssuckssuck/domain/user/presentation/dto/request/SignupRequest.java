package moofarm.ssuckssuck.domain.user.presentation.dto.request;

import moofarm.ssuckssuck.domain.oauth.domain.OauthServerType;
import moofarm.ssuckssuck.global.common.MainCategory;
import moofarm.ssuckssuck.global.common.SubCategory;

public record SignupRequest(
        String email,
        String name,
        String nickname,
        OauthServerType oauthServerType,
        MainCategory mainCategory,
        SubCategory subCategory
) {
}
