package moofarm.ssuckssuck.domain.group.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import moofarm.ssuckssuck.domain.group.domain.vo.GroupInfoVO;

import static lombok.AccessLevel.PROTECTED;


@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Group {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "group_id")
    private Long id;
    //private User user;
    private String title;
    private String description;
    private boolean isBookmark;
    private int participantsCount;

    @Builder
    public Group(String title, String description, int participantsCount) {
        this.title = title;
        this.description = description;
        this.participantsCount = participantsCount;
    }

    public GroupInfoVO getGroupInfo() {
        return new GroupInfoVO(
                title,
                description,
                isBookmark,
                participantsCount
        );
    }

    public static Group createGroup(String title, String description) {
        return Group.builder()
                .title(title)
                .description(description)
                .participantsCount(0)
                .build();
    }
}
