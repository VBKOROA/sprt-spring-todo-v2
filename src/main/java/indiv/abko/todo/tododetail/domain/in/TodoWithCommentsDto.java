package indiv.abko.todo.tododetail.domain.in;

import indiv.abko.todo.tododetail.domain.out.CommentSpec;
import indiv.abko.todo.tododetail.domain.out.TodoSpec;

import java.util.List;

public record TodoWithCommentsDto(
        TodoSpec todo,
        List<CommentSpec> comments
) {
}
