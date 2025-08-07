package indiv.abko.todo.tododetail.adapter.out;

import indiv.abko.todo.todo.application.port.in.TodoUseCaseFacade;
import indiv.abko.todo.todo.application.port.in.command.GetTodoCommand;
import indiv.abko.todo.todo.application.usecase.GetTodoUseCase;
import indiv.abko.todo.tododetail.domain.out.TodoDetailTodoPort;
import indiv.abko.todo.tododetail.domain.out.TodoSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TodoDetailTodoAdapter implements TodoDetailTodoPort {
    private final TodoUseCaseFacade todoUseCaseFacade;

    @Override
    public TodoSpec getTodoById(long todoId) {
        GetTodoCommand getTodoCommand = new GetTodoCommand(todoId);
        var todo = todoUseCaseFacade.getTodo(getTodoCommand);
        return TodoSpec.builder()
                .id(todo.getId())
                .title(todo.getTitle().getTitle())
                .authorName(todo.getAuthor().getName())
                .modifiedAt(todo.getModifiedAt())
                .content(todo.getContent().getContent())
                .createdAt(todo.getCreatedAt())
                .authorId(todo.getAuthor().getId())
                .build();
    }
}
