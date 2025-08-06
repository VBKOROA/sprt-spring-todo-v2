package indiv.abko.todo.member.domain.port.out;

import indiv.abko.todo.member.domain.Member;

import java.util.Optional;

public interface MemberRepository {
    Optional<Member> findByIdForPublic(long id);
    boolean isExistsByEmail(String email);
}
