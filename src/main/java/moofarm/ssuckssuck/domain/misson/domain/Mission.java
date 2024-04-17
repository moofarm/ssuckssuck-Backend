package moofarm.ssuckssuck.domain.misson.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moofarm.ssuckssuck.domain.certification.domain.Certification;
import moofarm.ssuckssuck.domain.group.domain.Group;
import moofarm.ssuckssuck.domain.misson.domain.vo.MissionInfoVO;
import moofarm.ssuckssuck.domain.misson.exception.UserIsNotMissionHostException;
import moofarm.ssuckssuck.domain.user.domain.User;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Mission {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<Certification> certificationList = new ArrayList<>();

    private LocalDate dueDate;
    private boolean bookmark;
    private Integer missionFrequency;
    private boolean missionStatus;
    private DayOfWeek dayOfWeek;

    @Enumerated(EnumType.STRING)
    private TargetCount targetCount;

    @Builder
    public Mission(User user, Group group, LocalDate dueDate, boolean bookmark, Integer missionFrequency, boolean missionStatus, TargetCount targetCount, DayOfWeek dayOfWeek) {
        this.user = user;
        this.group = group;
        this.dueDate = dueDate;
        this.bookmark = bookmark;
        this.missionFrequency = missionFrequency;
        this.missionStatus = missionStatus;
        this.targetCount = targetCount;
        this.dayOfWeek = dayOfWeek;
    }

    public static Mission createMission(User user, Group group, LocalDate dueDate, TargetCount targetCount) {
        return builder()
                .user(user)
                .group(group)
                .dueDate(dueDate)
                .bookmark(false)
                .missionFrequency(0)
                .missionStatus(false)
                .targetCount(targetCount)
                .dayOfWeek(LocalDate.now().plusDays(1).getDayOfWeek())
                .build();
    }

    public MissionInfoVO getMissionInfoVO() {
        return new MissionInfoVO(
                id,
                dueDate,
                bookmark,
                missionFrequency,
                missionStatus,
                targetCount,
                dayOfWeek
        );
    }

    public void addMissionFrequency() {
        this.missionFrequency++;
    }

    public void resetMissionFrequency() {
        this.missionFrequency = 0;
    }

    public void bookmarkMission() {
        this.bookmark = true;
    }

    public void updateMissionStatus() {
        this.missionStatus = true;
    }

    public void resetMissionStatus() {
        this.missionStatus = false;
    }

    public void validUserIsHost(User user) {
        if (!this.user.equals(user)) {
            throw UserIsNotMissionHostException.EXCEPTION;
        }
    }
}
