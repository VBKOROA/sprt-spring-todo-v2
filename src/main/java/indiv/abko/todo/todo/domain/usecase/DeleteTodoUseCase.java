package indiv.abko.todo.todo.domain.usecase;

import indiv.abko.todo.todo.domain.port.out.TodoCommentPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import indiv.abko.todo.global.exception.BusinessException;
import indiv.abko.todo.todo.domain.port.in.command.DeleteTodoCommand;
import indiv.abko.todo.todo.domain.port.out.TodoRepository;
import indiv.abko.todo.todo.domain.Todo;
import indiv.abko.todo.todo.domain.TodoExceptionEnum;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteTodoUseCase {
    private final TodoRepository todoRepo;
    private final TodoCommentPort todoCommentPort;

    /**
     * 주어진 Todo ID와 요청자 ID를 사용하여 Todo를 삭제합니다.
     *
     * @param command 삭제할 Todo의 ID와 요청자 ID를 포함한 명령 객체
     * @throws BusinessException Todo가 존재하지 않거나 권한이 없을 때 발생
     */
    @Transactional
    public void execute(DeleteTodoCommand command) {
        final Todo todo = todoRepo.findSummary(command.todoId())
            .orElseThrow(() -> new BusinessException(TodoExceptionEnum.TODO_NOT_FOUND));
        todo.shouldHaveAuth(command.requesterId());
        todoRepo.delete(todo);
        todoCommentPort.deleteAllByTodoId(command.todoId());
    }
}
