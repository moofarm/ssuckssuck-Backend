package moofarm.ssuckssuck.domain.misson.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TargetCount {
    UP_TO_TWO_TIMES(1),
    THREE_TIMES_OR_MORE(3),
    FIVE_TIMES_OR_MORE(5),
    DAILY(7);

    private final int MinNum;
}
