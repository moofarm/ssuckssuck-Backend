package moofarm.ssuckssuck.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SsuckException extends RuntimeException{

    private ErrorCode errorCode;
}
