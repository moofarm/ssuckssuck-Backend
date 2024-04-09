package moofarm.ssuckssuck.domain.group.domain;

import com.sun.tools.javac.Main;
import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

import lombok.*;
import moofarm.ssuckssuck.domain.group.domain.vo.GroupInfoVO;
import moofarm.ssuckssuck.domain.user.domain.User;
import moofarm.ssuckssuck.global.common.MainCategory;
import moofarm.ssuckssuck.global.common.SubCategory;

import static lombok.AccessLevel.PROTECTED;


@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Group {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "group_id")
    private Long id;

    //@Column(name = "user_id")
    //private User user;
    private String title;
    private String description;
    private int participantsCount;

    @Enumerated(EnumType.STRING)
    private MainCategory mainCategory;
    @Enumerated(EnumType.STRING)
    private SubCategory subCategory;

    @Builder
    public Group(String title, String description, int participantsCount, MainCategory mainCategory, SubCategory subCategory) {
        this.title = title;
        this.description = description;
        this.participantsCount = participantsCount;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
    }

    public GroupInfoVO getGroupInfo() {
        return new GroupInfoVO(
                title,
                description,
                participantsCount,
                mainCategory,
                subCategory
        );
    }

    public static Group createGroup(String title, String description, MainCategory mainCategory, SubCategory subCategory) {
        return Group.builder()
                .title(title)
                .description(description)
                .participantsCount(0)
                .mainCategory(mainCategory)
                .subCategory(subCategory)
                .build();
    }
}
