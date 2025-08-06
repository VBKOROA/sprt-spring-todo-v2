package indiv.abko.todo.member.application.port.in;

import indiv.abko.todo.member.application.port.in.command.SignUpCommand;
import indiv.abko.todo.member.domain.Member;

public interface SignUpUseCase {
    Member signUp(SignUpCommand signUpCommand);
}
