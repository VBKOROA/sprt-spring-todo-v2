package indiv.abko.todo.member.adapter.out.security;

import indiv.abko.todo.member.domain.port.out.MemberPasswordEncoder;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component("MemberPasswordEncoder")
public class JBcryptPasswordEncoder implements MemberPasswordEncoder {
    @Override
    public String encode(final String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }

    @Override
    public boolean matches(final String rawPassword, final String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
}
