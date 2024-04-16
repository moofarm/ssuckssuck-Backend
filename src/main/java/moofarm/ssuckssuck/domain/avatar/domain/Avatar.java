package moofarm.ssuckssuck.domain.avatar.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moofarm.ssuckssuck.domain.avatar.domain.vo.AvatarInfoVO;

import java.time.LocalDate;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;
import static moofarm.ssuckssuck.domain.avatar.domain.Grade.SEED;
import static moofarm.ssuckssuck.domain.avatar.domain.Grade.SPROUT;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Avatar {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private Integer experience;
    private LocalDate birthday;
    private String characterImage;
    private Integer expToNextGrade;
    private Integer expDiffCurrGrade;

    @Enumerated(STRING)
    private Grade grade;

    @Builder
    public Avatar(Integer experience, LocalDate birthday, String characterImage, Grade grade, Integer expToNextGrade, Integer expDiffCurrGrade) {
        this.experience = experience;
        this.birthday = birthday;
        this.characterImage = characterImage;
        this.grade = grade;
        this.expToNextGrade = expToNextGrade;
        this.expDiffCurrGrade = expDiffCurrGrade;
    }

    public static Avatar createAvatar() {
        return builder()
                .experience(0)
                .birthday(LocalDate.now())
                .characterImage(SEED.getAvatarImage())
                .expToNextGrade(SPROUT.getRequiredExperience())
                .expDiffCurrGrade(0)
                .grade(Grade.SEED)
                .build();
    }

    public AvatarInfoVO getAvatarInfoVO() {
        return new AvatarInfoVO(
                id,
                experience,
                birthday,
                characterImage,
                expToNextGrade,
                expDiffCurrGrade,
                grade
        );
    }

    public void addExperience(Integer experience) {
        this.experience += experience;
    }

    public void updateGrade(Grade grade) {
        this.grade = grade;
        this.characterImage = grade.getAvatarImage();
    }

    public void updateGradeExpInfo(Integer expToNextGrade, Integer expDiffCurrGrade) {
        this.expToNextGrade = expToNextGrade;
        this.expDiffCurrGrade = expDiffCurrGrade;
    }

    public void resetRadishInfo() {
        this.experience = 0;
        this.characterImage = SEED.getAvatarImage();
        this.grade = Grade.SEED;
        this.expToNextGrade = SPROUT.getRequiredExperience();
        this.expDiffCurrGrade = 0;
    }
}
