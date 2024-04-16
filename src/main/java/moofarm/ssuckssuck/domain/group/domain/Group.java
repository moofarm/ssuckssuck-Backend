package moofarm.ssuckssuck.domain.group.domain;

import jakarta.persistence.*;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

import lombok.*;
import moofarm.ssuckssuck.domain.group.domain.vo.GroupInfoVO;
import moofarm.ssuckssuck.domain.misson.domain.Mission;
import moofarm.ssuckssuck.global.common.MainCategory;
import moofarm.ssuckssuck.global.common.SubCategory;
import moofarm.ssuckssuck.global.database.BaseEntity;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;


@Entity
@Getter
@Table(name = "MissionRoom")
@NoArgsConstructor(access = PROTECTED)
public class Group extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "group_id")
    private Long id;

    private String title;
    private String description;
    private int participantsCount;

    @Enumerated(EnumType.STRING)
    private MainCategory mainCategory;
    @Enumerated(EnumType.STRING)
    private SubCategory subCategory;

    @OneToMany(mappedBy = "group", cascade = ALL)
    private List<Mission> missionList = new ArrayList<>();

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
                id,
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
    public void addParticipant() {this.participantsCount++;}
    public void subtractParticipant() {this.participantsCount--;}

}
