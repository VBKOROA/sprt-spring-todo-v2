package indiv.abko.todo.todo.domain.usecase;

import indiv.abko.todo.global.exception.BusinessException;
import indiv.abko.todo.todo.domain.port.in.TodoIdShouldExistUseCase;
import indiv.abko.todo.todo.domain.exception.TodoExceptionEnum;
import indiv.abko.todo.todo.domain.port.out.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultTodoIdShouldExistUseCase implements TodoIdShouldExistUseCase {
    private final TodoRepository todoRepo;

    @Override
    public void execute(final long todoId) {
        if (todoRepo.isExistBy(todoId) == false) {
            throw new BusinessException(TodoExceptionEnum.TODO_NOT_FOUND);
        }
    }
}
