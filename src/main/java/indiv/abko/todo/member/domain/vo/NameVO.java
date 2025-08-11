package indiv.abko.todo.member.domain.vo;

import indiv.abko.todo.global.exception.BusinessException;
import indiv.abko.todo.member.domain.MemberExceptionEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NameVO {
    private String value;
    private static final int MIN = 2;
    private static final int MAX = 10;

    public static NameVO fromRawName(final String name) {
        if(StringUtils.hasText(name) == false) {
            throw new BusinessException(MemberExceptionEnum.MEMBER_NAME_EMPTY);
        }

        if(MIN > name.length() || name.length() > MAX) {
            throw new BusinessException(MemberExceptionEnum.MEMBER_NAME_LENGTH_NOT_VALID);
        }
        return new NameVO(name);
    }

    public static NameVO reconstitute(final String name) {
        return new NameVO(name);
    }
}
