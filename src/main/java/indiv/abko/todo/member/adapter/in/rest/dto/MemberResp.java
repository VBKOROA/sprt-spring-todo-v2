package indiv.abko.todo.member.adapter.in.rest.dto;

import indiv.abko.todo.member.domain.Member;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MemberResp(
        long id,
        String name,
        String email,
        LocalDateTime modifiedAt,
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
