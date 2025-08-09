package indiv.abko.todo.todo.domain.vo;

import indiv.abko.todo.global.exception.BusinessException;
import indiv.abko.todo.todo.domain.TodoExceptionEnum;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoTitleVO {
    private String title;

    public TodoTitleVO(final String title) {
        this.title = title;
    }

    public static TodoTitleVO fromRaw(final String title) {
        shouldValid(title);
        return new TodoTitleVO(title);
    }

    public static TodoTitleVO reconstitute(String title) {
        return new TodoTitleVO(title);
    }

    private static void shouldValid(final String title) {
        if(StringUtils.hasText(title) == false)  {
            throw new BusinessException(TodoExceptionEnum.TODO_TITLE_REQUIRED);
        }

        final boolean lengthNotValid = !(1 <= title.length() && title.length() <= 30);

        if (lengthNotValid) {
            throw new BusinessException(TodoExceptionEnum.TODO_TITLE_LENGTH_NOT_VALID);
        }
    }
}
