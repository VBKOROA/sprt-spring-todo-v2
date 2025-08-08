package indiv.abko.todo.todo.domain.usecase;

import indiv.abko.todo.todo.domain.port.in.TodoDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import indiv.abko.todo.global.exception.BusinessException;
import indiv.abko.todo.todo.domain.port.in.command.SearchTodosCommand;
import indiv.abko.todo.todo.domain.port.out.TodoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchTodosUseCase {
    private final TodoRepository todoRepo;

    /**
     * 검색 조건에 따라 Todo 목록을 조회한다.
     *
     * @param searchCommand 검색 조건을 포함한 명령 객체
     * @return 조건에 맞는 Todo Page
     * @throws BusinessException 조회 중 예외 발생 시 예외를 던짐
     */
    @Transactional(readOnly = true)
    public Page<TodoDto> execute(SearchTodosCommand searchCommand) {
        final var todoPage = todoRepo.searchSummaries(searchCommand.toCriteria(), searchCommand.pageable());
        return todoPage.map(TodoDto::from);
    }
}
