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
    public static MemberResp from(Member memberDto) {
        return MemberResp.builder()
                .id(memberDto.getId())
                .name(memberDto.getName().getValue())
                .email(memberDto.getEmail().getValue())
                .modifiedAt(memberDto.getModifiedAt())
                .createdAt(memberDto.getCreatedAt())
                .build();
    }
}
