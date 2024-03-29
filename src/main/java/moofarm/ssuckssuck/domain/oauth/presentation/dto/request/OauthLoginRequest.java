package moofarm.ssuckssuck.domain.oauth.presentation.dto.request;

import moofarm.ssuckssuck.domain.oauth.domain.OauthServerType;

public record OauthLoginRequest(
        OauthServerType oauthServerType,
        String code
) {
}
