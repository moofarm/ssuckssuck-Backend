package moofarm.ssuckssuck.domain.misson.domain.vo;

import moofarm.ssuckssuck.domain.misson.domain.TargetCount;

import java.time.DayOfWeek;
import java.time.LocalDate;

public record MissionInfoVO(
        Long id,
        LocalDate dueDate,
        boolean bookmark,
        Integer missionFrequency,
        boolean missionStatus,
        TargetCount targetCount,
        DayOfWeek dayOfWeek
) {
}
