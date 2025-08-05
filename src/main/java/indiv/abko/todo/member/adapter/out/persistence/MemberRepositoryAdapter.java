package indiv.abko.todo.member.adapter.out.persistence;

import indiv.abko.todo.member.adapter.out.persistence.entity.MemberJpaEntity;
import indiv.abko.todo.member.domain.Member;
import indiv.abko.todo.member.domain.port.out.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryAdapter implements MemberRepository{
    private final MemberJpaRepository memberJpaRepo;

    @Override
    public Optional<Member> findById(long id) {
        return memberJpaRepo.findById(id).map(MemberJpaEntity::toDomain);
    }
}
