package moofarm.ssuckssuck.global.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static moofarm.ssuckssuck.global.common.MainCategory.*;

@Getter
@AllArgsConstructor
public enum SubCategory {

    // 공부
    LANGUAGE("어학", STUDY),
    EXAM("수험", STUDY),
    PROGRAMMING("프로그래밍", STUDY),
    STUDY_ECT("공부 - 기타", STUDY),

    // 생활 습관
    EXERCISE("운동", LIFESTYLE),
    SELF_DEVELOPMENT("자기계발", LIFESTYLE),
    EATING("식습관", LIFESTYLE),
    LIFESTYLE_ECT("생활 습관 - 기타", LIFESTYLE),

    // 자격증
    ENGINEER("기사", CERTIFICATE),
    NCS("NCS", CERTIFICATE),
    COMPUTER("컴활", CERTIFICATE),
    HISTORY("한국사", CERTIFICATE),
    CERTIFICATE_ECT("자격증 - 기타", CERTIFICATE),

    // 취미
    READING("독서", HOBBY),
    PAINTING("미술", HOBBY),
    MUSIC("음악", HOBBY),
    HOBBY_ECT("취미 - 기타", HOBBY),

    // 경제
    SAVING_MONEY("절약", ECONOMY),
    INVEST("투자", ECONOMY),
    ECONOMY_ECT("경제 - 기타", ECONOMY);

    private final String message;
    private final MainCategory mainCategory;

    public boolean isPartOf(MainCategory mainCategory) {
        return this.mainCategory == mainCategory;
    }
}
