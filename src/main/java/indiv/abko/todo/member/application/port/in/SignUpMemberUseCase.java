package indiv.abko.todo.member.application.port.in;

import indiv.abko.todo.member.application.port.in.command.SignUpMemberCommand;
import indiv.abko.todo.member.domain.Member;

public interface SignUpMemberUseCase {
    Member signUp(SignUpMemberCommand signUpCommand);
}
