package indiv.abko.todo.member.adapter.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "회원 정보 수정 요청 DTO")
public record UpdateMemberReq(
        @NotBlank
        @Schema(description = "수정할 이름 / 2~10자", example = "김길동", requiredMode = Schema.RequiredMode.REQUIRED)
        String name
) {
}
