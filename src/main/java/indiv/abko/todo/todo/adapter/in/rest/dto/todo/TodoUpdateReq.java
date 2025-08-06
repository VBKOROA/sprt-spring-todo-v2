package indiv.abko.todo.todo.adapter.in.rest.dto.todo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Todo 업데이트 요청 DTO")
public record TodoUpdateReq(
    @NotBlank
    @Schema(description = "Todo 내용", example = "할 일은 이것", requiredMode = Schema.RequiredMode.REQUIRED)
    String content
) {

}
