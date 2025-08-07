package indiv.abko.todo.member.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateMemberReq(
        @NotBlank
        String name
) {
}
