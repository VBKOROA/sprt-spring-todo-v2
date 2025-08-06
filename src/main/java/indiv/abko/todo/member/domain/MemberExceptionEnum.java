package indiv.abko.todo.member.domain;

import indiv.abko.todo.global.exception.BusinessExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberExceptionEnum implements BusinessExceptionEnum {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 ID의 Member가 없습니다."),
    MEMBER_EMAIL_DUPLICATE(HttpStatus.CONFLICT, "이미 사용 중인 이메일입니다."),
    MEMBER_PASSWORD_EMPTY(HttpStatus.BAD_REQUEST, "비밀번호가 비어 있습니다."),
    MEMBER_PASSWORD_LENGTH_NOT_VALID(HttpStatus.BAD_REQUEST, "비밀번호 길이가 유효하지 않습니다."),
    MEMBER_EMAIL_EMPTY(HttpStatus.BAD_REQUEST, "이메일이 비어 있습니다."),
    MEMBER_EMAIL_NOT_VALID(HttpStatus.BAD_REQUEST, "이메일 형식이 올바르지 않습니다."), 
    MEMBER_NAME_EMPTY(HttpStatus.BAD_REQUEST, "이름이 비어 있습니다."), 
    MEMBER_NAME_LENGTH_NOT_VALID(HttpStatus.BAD_REQUEST, "이름 길이가 유효하지 않습니다.");

    private final HttpStatus status;
    private final String message;
}
