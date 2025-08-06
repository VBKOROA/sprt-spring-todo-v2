package indiv.abko.todo.member.adapter.in.rest.dto;

import indiv.abko.todo.member.domain.Member;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record SignUpMemberResp(
        long id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static SignUpMemberResp from(Member member) {
        return SignUpMemberResp.builder()
                .id(member.getId())
                .name(member.getName().getValue())
                .createdAt(member.getCreatedAt())
                .modifiedAt(member.getModifiedAt())
                .build();
    }
}
