package indiv.abko.todo.member.adapter.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "로그인 요청 DTO")
public record LoginReq(
        @NotBlank
        @Schema(description = "이메일", example = "test@test.com", requiredMode = Schema.RequiredMode.REQUIRED)
        String email,
        @NotBlank
        @Schema(description = "비밀번호", example = "password", requiredMode = Schema.RequiredMode.REQUIRED)
        String password
) {
}
