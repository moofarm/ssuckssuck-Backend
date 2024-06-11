package moofarm.ssuckssuck.domain.certificationLike.domain.repository;

import moofarm.ssuckssuck.domain.certification.domain.Certification;
import moofarm.ssuckssuck.domain.certificationLike.domain.CertificationLike;
import moofarm.ssuckssuck.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CertificationLikeRepository extends JpaRepository<CertificationLike, Long> {
    Optional<CertificationLike> findByCertificationAndUser(Certification certification, User user);
}
