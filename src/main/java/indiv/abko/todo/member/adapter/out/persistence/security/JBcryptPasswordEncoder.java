package indiv.abko.todo.member.adapter.out.persistence.security;

import indiv.abko.todo.member.domain.port.out.PasswordEncoder;
import indiv.abko.todo.member.domain.vo.EncodedPasswordVO;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component("MemberPasswordEncoder")
public class JBcryptPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(final String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }

    @Override
    public boolean matches(final String rawPassword, final EncodedPasswordVO encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword.getValue());
    }
}
