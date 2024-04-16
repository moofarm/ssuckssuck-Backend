package moofarm.ssuckssuck.domain.misson.presentation.dto.request;

import jakarta.validation.constraints.Future;
import moofarm.ssuckssuck.domain.misson.domain.TargetCount;

import java.time.LocalDate;

public record CreateMissionRequest(
        @Future
        LocalDate dueDate,
        TargetCount targetCount
) {
}
