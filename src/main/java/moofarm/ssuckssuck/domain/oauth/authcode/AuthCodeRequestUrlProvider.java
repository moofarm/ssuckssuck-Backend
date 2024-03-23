package moofarm.ssuckssuck.domain.oauth.authcode;

import moofarm.ssuckssuck.domain.oauth.domain.OauthServerType;

public interface AuthCodeRequestUrlProvider {

    OauthServerType supportServer();

    String provide();
}
