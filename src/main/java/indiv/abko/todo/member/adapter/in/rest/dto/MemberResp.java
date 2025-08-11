package indiv.abko.todo.member.adapter.in.rest.dto;

import indiv.abko.todo.member.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Schema(description = "회원 정보 응답 DTO")
@Builder
public record MemberResp(
        @Schema(description = "회원 ID", example = "1")
        long id,
        @Schema(description = "이름", example = "홍길동")
        String name,
        @Schema(description = "이메일", example = "test@test.com")
        String email,
        @Schema(description = "수정일", example = "2025-01-01T12:00:00")
        LocalDateTime modifiedAt,
        @Schema(description = "생성일", example = "2025-01-01T12:00:00")
        LocalDateTime createdAt
) {
    public static MemberResp from(final Member member) {
        return MemberResp.builder()
                .id(member.getId())
                .name(member.getName().getValue())
                .email(member.getEmail().getValue())
                .modifiedAt(member.getModifiedAt())
                .createdAt(member.getCreatedAt())
                .build();
    }
}
