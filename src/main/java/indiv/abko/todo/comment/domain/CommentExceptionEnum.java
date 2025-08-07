package indiv.abko.todo.comment.domain;

import indiv.abko.todo.global.exception.BusinessExceptionEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CommentExceptionEnum implements BusinessExceptionEnum {
    COMMENT_LIMIT_EXCEEDED(HttpStatus.BAD_REQUEST, "댓글은 10개를 초과할 수 없습니다.");

    private final HttpStatus status;
    private final String message;

    CommentExceptionEnum(final HttpStatus status, final String message) {
        this.status = status;
        this.message = message;
    }
}
