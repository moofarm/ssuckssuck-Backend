package moofarm.ssuckssuck.domain.report.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReportType {
    SPAM("스팸"),
    SEXUAL("음란물"),
    ILLEGAL("불법 정보"),
    HARMFUL("유해한 내용"),
    DISCRIMINATORY("차별적 표현"),
    PRIVACY("개인 정보 유출"),
    UNPLEASANT("불쾌한 게시글"),
    OTHER("기타");

    private String text;
}
