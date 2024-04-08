package moofarm.ssuckssuck.domain.user.domain.repository;

import moofarm.ssuckssuck.domain.oauth.domain.OauthServerType;
import moofarm.ssuckssuck.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByOauthServerTypeAndEmail(OauthServerType oauthServerType, String email);

    boolean existsByNickname(String nickname);
}
