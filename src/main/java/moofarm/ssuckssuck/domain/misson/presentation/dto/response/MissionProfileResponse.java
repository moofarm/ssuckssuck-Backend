package moofarm.ssuckssuck.domain.misson.presentation.dto.response;

import moofarm.ssuckssuck.domain.misson.domain.TargetCount;
import moofarm.ssuckssuck.domain.misson.domain.vo.MissionInfoVO;

import java.time.DayOfWeek;
import java.time.LocalDate;

public record MissionProfileResponse(
        Long groupId,
        Long missionId,
        LocalDate dueDate,
        boolean bookmark,
        Integer missionFrequency,
        boolean missionStatus,
        TargetCount targetCount,
        DayOfWeek dayOfWeek
) {
    public MissionProfileResponse(Long groupId, MissionInfoVO missionInfoVO) {
        this(groupId, missionInfoVO.id(), missionInfoVO.dueDate(), missionInfoVO.bookmark(), missionInfoVO.missionFrequency(),
                missionInfoVO.missionStatus(), missionInfoVO.targetCount(), missionInfoVO.dayOfWeek());
    }
}
