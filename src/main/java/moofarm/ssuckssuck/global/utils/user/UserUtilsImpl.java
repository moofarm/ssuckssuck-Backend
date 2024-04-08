package moofarm.ssuckssuck.global.utils.user;

import lombok.RequiredArgsConstructor;
import moofarm.ssuckssuck.domain.user.domain.User;
import moofarm.ssuckssuck.domain.user.domain.repository.UserRepository;
import moofarm.ssuckssuck.global.exception.UserNotFoundException;
import moofarm.ssuckssuck.global.utils.security.SecurityUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserUtilsImpl implements UserUtils{

    private final UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    @Override
    public User getUserFromSecurityContext() {
        Long currentUserId = SecurityUtils.getCurrentUserId();
        User user = getUserById(currentUserId);
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}
