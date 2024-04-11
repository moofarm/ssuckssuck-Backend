package moofarm.ssuckssuck.domain.character.domain.vo;

import moofarm.ssuckssuck.domain.character.domain.Grade;

import java.time.LocalDate;

public record AvatarInfoVO(
        Long characterId,
        Integer experience,
        LocalDate birthday,
        String characterImage,
        Integer expToNextGrade,
        Integer expDiffCurrGrade,
        Grade grade
) {
}
