package indiv.abko.todo.tododetail.domain.in;

public interface GetTodoWithCommentsUseCase {
    TodoWithCommentsDto execute(long todoId);
}
