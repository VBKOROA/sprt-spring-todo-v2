package indiv.abko.todo.member.adapter.in.rest.dto;

import indiv.abko.todo.member.domain.port.in.command.SignUpCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "회원가입 요청 DTO")
public record SignUpReq(
        @NotBlank
        @Schema(description = "이름 / 2~10자", example = "홍길동", requiredMode = Schema.RequiredMode.REQUIRED)
        String name,
        @NotBlank
        @Schema(description = "이메일", example = "test@test.com", requiredMode = Schema.RequiredMode.REQUIRED)
        String email,
        @NotBlank
        @Schema(description = "비밀번호 / 1~20자", example = "password", requiredMode = Schema.RequiredMode.REQUIRED)
        String password
) {
    public SignUpCommand toCommand() {
        return SignUpCommand.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
