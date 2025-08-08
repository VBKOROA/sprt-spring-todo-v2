package indiv.abko.todo.member.domain.port.in;

import indiv.abko.todo.member.domain.port.in.command.SignUpCommand;
import indiv.abko.todo.member.domain.port.in.dto.MemberDto;

public interface SignUpUseCase {
    MemberDto signUp(SignUpCommand signUpCommand);
}
