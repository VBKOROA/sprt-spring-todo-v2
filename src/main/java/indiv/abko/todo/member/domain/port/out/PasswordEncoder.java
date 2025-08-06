package indiv.abko.todo.member.domain.port.out;

import indiv.abko.todo.member.domain.vo.EncodedPasswordVO;

public interface PasswordEncoder {
    String encode(final String rawPassword);
    boolean matches(final String rawPassword, final String encodedPassword);
}
