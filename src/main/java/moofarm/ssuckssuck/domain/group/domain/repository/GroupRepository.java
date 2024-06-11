package moofarm.ssuckssuck.domain.group.domain.repository;

import moofarm.ssuckssuck.domain.group.domain.Group;
import moofarm.ssuckssuck.domain.user.domain.User;
import moofarm.ssuckssuck.global.common.SubCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Slice<Group> findAllByTitle(String title);
    Slice<Group> findAllByDescription(String description);

    Slice<Group> findAllBySubCategory(SubCategory subCategory, Pageable pageable);

    @Query(value = "SELECT * FROM mission_room WHERE MATCH(title, description) AGAINST(:keyword IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    Slice<Group> findALlByKeyword(@Param("keyword") String keyword, Pageable pageable);
}

