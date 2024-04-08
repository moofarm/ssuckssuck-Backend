package moofarm.ssuckssuck.global.utils.user;

import moofarm.ssuckssuck.domain.user.domain.User;

public interface UserUtils {

    User getUserById(Long id);

    User getUserFromSecurityContext();

    User getUserByEmail(String email);
}
