package indiv.abko.todo.member.domain.port.in;

import indiv.abko.todo.member.domain.Member;
import indiv.abko.todo.member.domain.port.in.command.SignUpCommand;

public interface SignUpUseCase {
    Member signUp(SignUpCommand signUpCommand);
}
