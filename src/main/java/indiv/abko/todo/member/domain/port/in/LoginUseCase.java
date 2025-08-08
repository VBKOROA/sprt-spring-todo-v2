package indiv.abko.todo.member.domain.port.in;

public interface LoginUseCase {
    String execute(String email, String password);
}
