package indiv.abko.todo.tododetail.domain.in;

import java.util.List;

public record TodoWithCommentsDto(
        TodoDto todo,
        List<CommentDto> comments
) {
}
