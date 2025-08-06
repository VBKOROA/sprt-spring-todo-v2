package indiv.abko.todo.member.adapter.in.rest.dto;

import indiv.abko.todo.member.application.port.in.command.SignUpMemberCommand;

public record SignUpMemberReq(
        String name,
        String email,
        String password
) {
    public SignUpMemberCommand toCommand() {
        return SignUpMemberCommand.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
