package indiv.abko.todo.todo.domain.port.in;

import indiv.abko.todo.global.vo.ContentVO;
import indiv.abko.todo.todo.domain.Todo;
import indiv.abko.todo.todo.domain.port.in.command.CreateTodoCommand;
import indiv.abko.todo.todo.domain.port.out.TodoAuthorPort;
import indiv.abko.todo.todo.domain.port.out.TodoRepository;
import indiv.abko.todo.todo.domain.vo.TodoTitleVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface CreateTodoUseCase {
    Todo execute(CreateTodoCommand command);
}
