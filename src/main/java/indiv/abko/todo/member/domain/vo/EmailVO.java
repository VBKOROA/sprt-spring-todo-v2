package indiv.abko.todo.member.domain.vo;

import indiv.abko.todo.global.exception.BusinessException;
import indiv.abko.todo.member.domain.MemberExceptionEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailVO {
    private String value;
    private static final String EMAIL_VALID_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public static EmailVO fromRawEmail(final String email) {
        shouldValidOrThrow(email);
        return new EmailVO(email);
    }

    public static EmailVO reconstitute(final String email) {
        return new EmailVO(email);
    }

    private static void shouldValidOrThrow(String email) {
        if(StringUtils.hasText(email) == false) {
            throw new BusinessException(MemberExceptionEnum.MEMBER_EMAIL_EMPTY);
        }

        if(email.matches(EMAIL_VALID_REGEX) == false) {
            throw new BusinessException(MemberExceptionEnum.MEMBER_EMAIL_NOT_VALID);
        }
    }
}
