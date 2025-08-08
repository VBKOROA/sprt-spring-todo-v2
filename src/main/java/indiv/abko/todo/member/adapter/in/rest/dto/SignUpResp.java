package indiv.abko.todo.member.adapter.in.rest.dto;

import indiv.abko.todo.member.domain.port.in.dto.MemberDto;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record SignUpResp(
        long id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static SignUpResp from(MemberDto member) {
        return SignUpResp.builder()
                .id(member.id())
                .name(member.name())
                .createdAt(member.createdAt())
                .modifiedAt(member.modifiedAt())
                .build();
    }
}
