package indiv.abko.todo.comment.adapter.persistence;

import indiv.abko.todo.comment.domain.Comment;
import indiv.abko.todo.comment.domain.out.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryAdapter implements CommentRepository {
    private final CommentJpaRepository commentJpaRepo;

    @Override
    public Comment save(Comment comment) {
        var entity = CommentJpaEntity.from(comment);
        var saved = commentJpaRepo.save(entity);
        return saved.toDomain();
    }

    @Override
    public int countByTodoId(long todoId) {
        return commentJpaRepo.countByTodoId(todoId);
    }

    @Override
    public List<Comment> findAllBy(long todoId) {
        var entities = commentJpaRepo.findAllByTodoId(todoId);
        return entities.stream().map(CommentJpaEntity::toDomain).toList();
    }

    @Override
    public void deleteAllBy(long todoId) {
        commentJpaRepo.deleteAllByTodoId(todoId);
    }

    @Override
    public Optional<Comment> findById(long id) {
        var entity = commentJpaRepo.findById(id);
        return entity.map(CommentJpaEntity::toDomain);
    }
}
