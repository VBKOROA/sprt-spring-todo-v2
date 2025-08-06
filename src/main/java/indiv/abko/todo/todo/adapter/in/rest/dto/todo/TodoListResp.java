package indiv.abko.todo.todo.adapter.in.rest.dto.todo;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.springframework.data.domain.Page;

@Schema(description = "Todo 리스트 응답 DTO")
@Builder
public record TodoListResp(
    @Schema(description = "Todo 목록")
    List<TodoResp> todos,
    int pageNumber,
    int totalPages
) {
}
