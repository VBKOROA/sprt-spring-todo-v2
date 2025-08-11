package indiv.abko.todo.member.adapter.in.rest.dto;

import indiv.abko.todo.member.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Schema(description = "회원가입 응답 DTO")
@Builder
public record SignUpResp(
        @Schema(description = "회원 ID", example = "1")
        long id,
        @Schema(description = "이름", example = "홍길동")
        String name,
        @Schema(description = "생성일", example = "2025-01-01T12:00:00")
        LocalDateTime createdAt,
        @Schema(description = "수정일", example = "2025-01-01T12:00:00")
        LocalDateTime modifiedAt
) {
    public static SignUpResp from(final Member member) {
        return SignUpResp.builder()
                .id(member.getId())
                .name(member.getName().getValue())
                .createdAt(member.getCreatedAt())
                .modifiedAt(member.getModifiedAt())
                .build();
    }
}
