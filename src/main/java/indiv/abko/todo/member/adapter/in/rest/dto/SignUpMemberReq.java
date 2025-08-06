package indiv.abko.todo.member.adapter.in.rest.dto;

import indiv.abko.todo.member.application.port.in.command.SignUpCommand;

public record SignUpMemberReq(
        String name,
        String email,
        String password
) {
    public SignUpCommand toCommand() {
        return SignUpCommand.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
