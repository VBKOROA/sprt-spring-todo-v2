package indiv.abko.todo.todo.domain.usecase;

import indiv.abko.todo.todo.domain.port.in.CreateTodoUseCase;
import indiv.abko.todo.todo.domain.port.out.TodoAuthorPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import indiv.abko.todo.todo.domain.port.in.command.CreateTodoCommand;
import indiv.abko.todo.todo.domain.port.out.TodoRepository;
import indiv.abko.todo.todo.domain.Todo;
import indiv.abko.todo.global.vo.ContentVO;
import indiv.abko.todo.todo.domain.vo.TodoTitleVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateTodoUseCaseService implements CreateTodoUseCase {
    private final TodoRepository todoRepo;
    private final TodoAuthorPort todoAuthorPort;

    /**
     * 주어진 명령 객체를 기반으로 새로운 Todo 항목을 생성하여 저장소에 저장합니다.
     * 작성자 정보를 조회하고, 제목과 내용을 값 객체로 감싸 Todo 엔티티를 생성합니다.
     *
     * @param createCommand 생성할 Todo의 작성자 ID, 제목, 내용의 정보를 담은 명령 객체
     * @return 저장된 Todo 엔티티
     */
    @Override
    @Transactional
    public Todo execute(final CreateTodoCommand createCommand) {
        final var author = todoAuthorPort.getAuthor(createCommand.memberId());
        final Todo todo = Todo.builder()
                .author(author)
                .content(ContentVO.fromRawContent(createCommand.content()))
                .title(new TodoTitleVO(createCommand.title()))
                .build();
        return todoRepo.save(todo);
    }
}
