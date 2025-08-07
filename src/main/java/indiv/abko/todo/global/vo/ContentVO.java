package indiv.abko.todo.global.vo;

import indiv.abko.todo.global.exception.BusinessException;
import indiv.abko.todo.todo.domain.exception.TodoExceptionEnum;
import lombok.*;
import org.springframework.util.StringUtils;

@Getter
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ContentVO {
    private String content;

    public static ContentVO fromRawContent(final String content) {
        shouldValid(content);
        return new ContentVO(content);
    }

    public static ContentVO reconstitute(final String content) {
        return new ContentVO(content);
    }

    private static void shouldValid(final String content) {
        if(StringUtils.hasText(content) == false)  {
            throw new BusinessException(TodoExceptionEnum.CONTENT_REQUIRED);
        }

        final boolean notValid = !(1 <= content.length() && content.length() <= 200);

        if(notValid) {
            throw new BusinessException(TodoExceptionEnum.CONTENT_LENGTH_NOT_VALID);
        }
    }
}
