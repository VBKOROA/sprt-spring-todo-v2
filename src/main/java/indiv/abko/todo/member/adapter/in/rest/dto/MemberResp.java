package indiv.abko.todo.member.adapter.in.rest.dto;

import indiv.abko.todo.member.application.port.in.dto.MemberDto;
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
    public static MemberResp from(MemberDto memberDto) {
        return MemberResp.builder()
                .id(memberDto.id())
                .name(memberDto.name())
                .email(memberDto.email())
                .modifiedAt(memberDto.modifiedAt())
                .createdAt(memberDto.createdAt())
                .build();
    }
}
