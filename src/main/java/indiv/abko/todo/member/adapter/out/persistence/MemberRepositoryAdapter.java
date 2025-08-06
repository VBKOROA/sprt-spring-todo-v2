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

    @Override
    public boolean isExistsByEmail(String email) {
        return memberJpaRepo.existsByEmail(email);
    }

    @Override
    public Member create(final Member member) {
        final MemberJpaEntity memberJpaEntity = MemberJpaEntity.fromDomain(member);
        final MemberJpaEntity savedEntity = memberJpaRepo.save(memberJpaEntity);
        return savedEntity.toDomain();
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        final var memberEntity = memberJpaRepo.findByEmail(email);
        return memberEntity.map(MemberJpaEntity::toDomain);
    }
}
