package moofarm.ssuckssuck.domain.certification.domain.repository;

import moofarm.ssuckssuck.domain.certification.domain.Certification;
import moofarm.ssuckssuck.domain.group.domain.Group;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CertificationRepository extends JpaRepository<Certification, Long> {

    Optional<Certification> findById(Long id);
    Slice<Certification> findAllByGroup(Group group, Pageable pageable);
}
