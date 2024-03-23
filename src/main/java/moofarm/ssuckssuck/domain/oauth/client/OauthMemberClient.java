package moofarm.ssuckssuck.domain.oauth.client;

import moofarm.ssuckssuck.domain.oauth.domain.OauthMember;
import moofarm.ssuckssuck.domain.oauth.domain.OauthServerType;

public interface OauthMemberClient {

    OauthServerType supportServer();

    OauthMember fetch(String code);
}
