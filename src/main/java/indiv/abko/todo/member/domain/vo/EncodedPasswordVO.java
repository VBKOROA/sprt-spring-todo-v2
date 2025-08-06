package indiv.abko.todo.member.domain.vo;

import indiv.abko.todo.global.exception.BusinessException;
import indiv.abko.todo.member.domain.MemberExceptionEnum;
import indiv.abko.todo.member.domain.port.out.PasswordEncoder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EncodedPasswordVO {
    private static final int MIN = 1;
    private static final int MAX = 20;
    private String value;

    public static EncodedPasswordVO fromRawPassword(String password, PasswordEncoder passwordEncoder) {
        shouldValidOrThrow(password);
        return new EncodedPasswordVO(passwordEncoder.encode(password));
    }

    private static void shouldValidOrThrow(String password) {
        if(StringUtils.hasText(password) == false){
            throw new BusinessException(MemberExceptionEnum.MEMBER_PASSWORD_EMPTY);
        }

        if(password.length() > MAX || MIN > password.length()) {
            throw new BusinessException(MemberExceptionEnum.MEMBER_PASSWORD_LENGTH_NOT_VALID);
        }
    }

    public static EncodedPasswordVO reconstitute(final String password) {
        return new EncodedPasswordVO(password);
    }
}
