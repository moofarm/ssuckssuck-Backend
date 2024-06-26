package moofarm.ssuckssuck.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode{
    /* 400 BAD_REQUEST : 잘못된 요청 */
    NO_ERROR_TYPE(400, "유효하지 않은 요청입니다."),
    INCONSISTENCY_CATEGORY(400, "카테고리 분류가 일치하지 않습니다."),

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    INVALID_ACCESS_TOKEN(401, "Access 토큰이 유효하지 않습니다."),
    INVALID_REFRESH_TOKEN(401, "리프레시 토큰이 유효하지 않습니다."),
    INVALID_TOKEN(401, "토큰이 유효하지 않습니다."),
    EXPIRED_TOKEN(401, "토큰이 만료되었습니다."),

    /* 403 Forbidden : 권한이 없음 */
    REGISTER_EXPIRED_TOKEN(403, "만료된 리프레쉬 토큰입니다."),
    NOT_HOST_MISSION(403, "해당하는 미션의 권한이 없습니다."),
    NOT_OWNER_CERTIFICATION(403, "해당하는 미션 인증의 접근 권한이 없습니다."),

    /* 404 NOT_FOUND : Resource를 찾을 수 없음 */
    USER_NOT_FOUND(404, "해당하는 정보의 사용자를 찾을 수 없습니다."),
    MISSION_NOT_FOUND(404, "해당하는 정보의 미션을 찾을 수 없습니다."),
    GROUP_NOT_FOUND(404, "해당하는 정보의 미션방을 찾을 수 없습니다."),
    DUPLICATION_PARTICIPATION_GROUP(404, "해당하는 그룹에 중복 참여입니다."),
    CERTIFICATION_NOT_FOUND(404, "해당하는 미션 인증 이미지를 찾을 수 없습니다."),
    REPORT_NOT_FOUND(404, "해당하는 정보의 신고를 찾을 수 없습니다."),
    BAD_FILE_EXTENSION(404,  "파일 확장자 오류입니다."),
    IMAGE_SIZE_EXTENSION(404,  "파일 크기 오류입니다."),
    FILE_EMPTY(404,  "해당 파일이 비어 있습니다."),
    FILE_UPLOAD_FAIL(404,  "파일 업로드에 실패하였습니다."),

    /* 409 Conflict : 사용자의 요청이 서버의 상태와 충돌 */
    NICKNAME_DUPLICATION(409, "닉네임이 중복됩니다."),

    /* 500 SERVER_ERROR */
    INTERNAL_SERVER_ERROR(500, "서버 에러입니다.");

    private final int status;
    private final String reason;
}

