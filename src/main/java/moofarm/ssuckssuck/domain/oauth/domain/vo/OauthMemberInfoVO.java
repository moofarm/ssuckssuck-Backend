package moofarm.ssuckssuck.domain.oauth.domain.vo;

import moofarm.ssuckssuck.domain.oauth.domain.OauthServerType;

public record OauthMemberInfoVO(
        String email,
        String name,
        OauthServerType oauthServerType
) {
}
