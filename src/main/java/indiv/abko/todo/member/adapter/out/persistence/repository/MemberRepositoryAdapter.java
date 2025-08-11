package indiv.abko.todo.member.adapter.out.persistence.repository;

import indiv.abko.todo.global.exception.BusinessException;
import indiv.abko.todo.member.adapter.out.persistence.MemberJpaEntity;
import indiv.abko.todo.member.domain.Member;
import indiv.abko.todo.member.domain.MemberExceptionEnum;
import indiv.abko.todo.member.domain.port.out.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryAdapter implements MemberRepository {
    private final MemberJpaRepository memberJpaRepo;

    @Override
    public Optional<Member> findById(long id) {
        return memberJpaRepo.findById(id).map(MemberJpaEntity::toDomain);
    }

    @Override
    public boolean isExistsByEmail(final String email) {
        return memberJpaRepo.existsByEmail(email);
    }

    @Override
    public Member save(final Member member) {
        final var memberJpaEntity = MemberJpaEntity.fromDomain(member);
        final MemberJpaEntity savedEntity = memberJpaRepo.save(memberJpaEntity);
        return savedEntity.toDomain();
    }

    @Override
    public Optional<Member> findByEmail(final String email) {
        final Optional<MemberJpaEntity> memberEntity = memberJpaRepo.findByEmail(email);
        return memberEntity.map(MemberJpaEntity::toDomain);
    }

    @Override
    public void deleteById(final long requesterId) {
        memberJpaRepo.deleteById(requesterId);
    }

    @Override
    public boolean isExistsById(final long requesterId) {
        return memberJpaRepo.existsById(requesterId);
    }

    @Override
    public Optional<String> findNameById(final long id) {
        return memberJpaRepo.findNameById(id).map(NameProjection::name);
    }
}
