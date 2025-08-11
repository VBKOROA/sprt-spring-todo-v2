package indiv.abko.todo.comment.domain;

import indiv.abko.todo.global.exception.BusinessExceptionEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CommentExceptionEnum implements BusinessExceptionEnum {
    COMMENT_LIMIT_EXCEEDED(HttpStatus.BAD_REQUEST, "댓글은 10개를 초과할 수 없습니다."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 댓글이 존재하지 않습니다."),
    PERMISSION_DENIED(HttpStatus.FORBIDDEN, "해당 댓글에 대한 권한이 없습니다.");

    private final HttpStatus status;
    private final String message;

    CommentExceptionEnum(final HttpStatus status, final String message) {
        this.status = status;
        this.message = message;
    }
}
