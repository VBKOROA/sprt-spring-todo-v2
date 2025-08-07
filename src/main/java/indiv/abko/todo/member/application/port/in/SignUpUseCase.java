package indiv.abko.todo.member.application.port.in;

import indiv.abko.todo.member.application.port.in.command.SignUpCommand;
import indiv.abko.todo.member.application.port.in.dto.MemberDto;

public interface SignUpUseCase {
    MemberDto signUp(SignUpCommand signUpCommand);
}
