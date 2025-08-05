package indiv.abko.todo.member.adapter.in.rest.dto;

import indiv.abko.todo.member.domain.Member;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MemberResp(
        String name,
        String email,
        LocalDateTime modifiedAt,
        LocalDateTime createdAt
) {
    public static MemberResp of(Member member) {
        return MemberResp.builder()
                .name(member.getName())
                .email(member.getEmail())
                .modifiedAt(member.getModifiedAt())
                .createdAt(member.getCreatedAt())
                .build();
    }
}
