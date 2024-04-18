package moofarm.ssuckssuck.domain.misson.domain.repository;

import moofarm.ssuckssuck.domain.group.domain.Group;
import moofarm.ssuckssuck.domain.misson.domain.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    Optional<Mission> findByGroup(Group group);
}