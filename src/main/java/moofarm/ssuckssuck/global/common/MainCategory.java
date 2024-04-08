package moofarm.ssuckssuck.global.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MainCategory {
    STUDY("공부"),
    LIFESTYLE("생활 습관"),
    CERTIFICATE("자격증"),
    HOBBY("취미"),
    ECONOMY("경제"),
    ETC("기타");

    private final String message;
}
