package indiv.abko.todo.comment.adapter.out.persistence;

import indiv.abko.todo.comment.domain.Comment;
import indiv.abko.todo.comment.domain.out.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryAdapter implements CommentRepository {
    private final CommentJpaRepository commentJpaRepo;

    @Override
    public Comment save(final Comment comment) {
        final var entity = CommentJpaEntity.from(comment);
        final CommentJpaEntity saved = commentJpaRepo.save(entity);
        return saved.toDomain();
    }

    @Override
    public int countByTodoId(final long todoId) {
        return commentJpaRepo.countByTodoId(todoId);
    }

    @Override
    public List<Comment> findAllBy(final long todoId) {
        final List<CommentJpaEntity> entities = commentJpaRepo.findAllByTodoId(todoId);
        return entities.stream().map(CommentJpaEntity::toDomain).toList();
    }

    @Override
    public void deleteAllBy(final long todoId) {
        commentJpaRepo.deleteAllByTodoId(todoId);
    }

    @Override
    public Optional<Comment> findById(final long id) {
        final Optional<CommentJpaEntity> entity = commentJpaRepo.findById(id);
        return entity.map(CommentJpaEntity::toDomain);
    }

    @Override
    public void deleteAllByAuthorId(final long authorId) {
        commentJpaRepo.deleteAllByAuthorId(authorId);
    }

    @Override
    public void delete(final long commentId) {
        commentJpaRepo.deleteById(commentId);
    }

    @Override
    public void deleteAllByTodoIds(List<Long> todoIds) {
        commentJpaRepo.deleteAllByTodoIdIn(todoIds);
    }
}
