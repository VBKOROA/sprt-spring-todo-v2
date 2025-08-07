package indiv.abko.todo.member.domain.port.out;

import indiv.abko.todo.member.domain.Member;

import java.util.Optional;

public interface MemberRepository {
    Optional<Member> findById(long id);
    boolean isExistsByEmail(String email);

    Member save(Member member);

    Optional<Member> findByEmail(String email);
}
