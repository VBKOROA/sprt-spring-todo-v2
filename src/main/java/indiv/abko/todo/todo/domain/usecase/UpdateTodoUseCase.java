package indiv.abko.todo.todo.domain.usecase;

import indiv.abko.todo.todo.domain.port.in.TodoDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import indiv.abko.todo.global.exception.BusinessException;
import indiv.abko.todo.todo.domain.port.in.command.UpdateTodoCommand;
import indiv.abko.todo.todo.domain.port.out.TodoRepository;
import indiv.abko.todo.todo.domain.Todo;
import indiv.abko.todo.todo.domain.TodoExceptionEnum;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateTodoUseCase {
    private final TodoRepository todoRepo;

    /**
     * 주어진 ID에 해당하는 Todo 항목을 업데이트합니다.
     *
     * @param updateCommand 업데이트할 Todo의 ID, 작성자 ID, 내용이 포함된 명령 객체
     *                      - {@code id}: 업데이트할 Todo의 고유 식별자
     *                      - {@code requesterId}: 인증 및 권한 확인에 사용할 작성자 ID
     *                      - {@code content}: 변경할 내용
     * @return 업데이트된 Todo 엔티티
     * @throws BusinessException Todo 항목을 찾을 수 없거나 권한이 없을 경우
     */
    @Transactional
    public Todo execute(UpdateTodoCommand updateCommand) {
        final Todo todo = todoRepo.findBy(updateCommand.todoId())
                .orElseThrow(() -> new BusinessException(TodoExceptionEnum.TODO_NOT_FOUND));
        todo.updateContent(updateCommand.content(), updateCommand.requesterId());
        return todoRepo.save(todo);
    }
}
