package indiv.abko.todo.member.adapter.out.persistence.repository;

import indiv.abko.todo.member.adapter.out.persistence.MemberJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<MemberJpaEntity, Long> {
    boolean existsByEmail(String email);
    Optional<MemberJpaEntity> findByEmail(String email);
    Optional<NameProjection> findNameById(long id);
    Optional<MemberJpaEntity> findById(long id);
}
