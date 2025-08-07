package indiv.abko.todo.comment.domain.in;

import indiv.abko.todo.comment.domain.Comment;
import indiv.abko.todo.global.vo.AuthorVO;
import indiv.abko.todo.global.vo.ContentVO;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CommentDto(
        long id,
        String content,
        long authorId,
        String authorName,
        long todoId,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static CommentDto from(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent().getContent())
                .authorId(comment.getAuthor().getId())
                .authorName(comment.getAuthor().getName())
                .todoId(comment.getTodoId())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .build();
    }
}
