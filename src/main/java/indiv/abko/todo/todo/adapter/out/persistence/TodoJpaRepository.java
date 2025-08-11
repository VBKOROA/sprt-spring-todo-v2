package indiv.abko.todo.todo.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoJpaRepository extends JpaRepository<TodoJpaEntity,Long> {
    List<TodoIdProjection> findAllByAuthorId(long authorId);

    void deleteAllByAuthorId(Long authorId);
}
