package indiv.abko.todo.member.application.port.in;

import indiv.abko.todo.member.domain.Member;
import jakarta.validation.constraints.NotBlank;

public interface UpdateMemberUseCase {
    Member update(long requesterId, String name);
}
