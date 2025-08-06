package indiv.abko.todo.member.domain;

import indiv.abko.todo.global.exception.BusinessExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberExceptionEnum implements BusinessExceptionEnum {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 ID의 Member가 없습니다."),
    MEMBER_EMAIL_DUPLICATE(HttpStatus.CONFLICT, "이미 사용 중인 이메일입니다.");

    private final HttpStatus status;
    private final String message;
}
