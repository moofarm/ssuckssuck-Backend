package moofarm.ssuckssuck.domain.report.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProcessingStatus {
    RECEIPT("대기"),
    COMPANION("반려"),
    PROCESS("처리");

    private String status;
}
