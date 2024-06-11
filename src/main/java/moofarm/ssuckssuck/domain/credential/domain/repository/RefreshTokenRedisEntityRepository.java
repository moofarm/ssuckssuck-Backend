package moofarm.ssuckssuck.domain.credential.domain.repository;

import moofarm.ssuckssuck.domain.credential.domain.RefreshTokenRedisEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRedisEntityRepository extends CrudRepository<RefreshTokenRedisEntity, String> {

    Optional<RefreshTokenRedisEntity> findByRefreshToken(String refreshToken);
}
