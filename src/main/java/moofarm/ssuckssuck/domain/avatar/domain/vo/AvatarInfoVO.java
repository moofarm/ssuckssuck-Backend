package moofarm.ssuckssuck.domain.avatar.domain.vo;

import moofarm.ssuckssuck.domain.avatar.domain.Grade;

import java.time.LocalDate;

public record AvatarInfoVO(
        Long characterId,
        Integer experience,
        LocalDate birthday,
        String characterImage,
        Integer expToNextGrade,
        Integer expDiffCurrGrade,
        Integer radishCount,
        Grade grade
) {
}
