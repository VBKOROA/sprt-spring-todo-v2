package indiv.abko.todo.member.domain.port.in.dto;

import indiv.abko.todo.member.domain.Member;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MemberDto(
        long id,
        String name,
        String email,
        LocalDateTime modifiedAt,
        LocalDateTime createdAt
) {
    public static MemberDto from(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .name(member.getName().getValue())
                .email(member.getEmail().getValue())
                .modifiedAt(member.getModifiedAt())
                .createdAt(member.getCreatedAt())
                .build();
    }
}
