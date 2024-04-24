package moofarm.ssuckssuck.domain.misson.domain.repository;

import moofarm.ssuckssuck.domain.group.domain.Group;
import moofarm.ssuckssuck.domain.misson.domain.Mission;
import moofarm.ssuckssuck.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    Optional<Mission> findByGroup(Group group);
    boolean existsByUserAndGroup(User user, Group group);
    List<Mission> findAllByUserAndBookmarkOrderByCreateDateDesc(User user, boolean bookmark);
}
