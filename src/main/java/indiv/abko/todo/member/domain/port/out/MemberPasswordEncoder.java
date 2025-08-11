package indiv.abko.todo.member.domain.port.out;

public interface MemberPasswordEncoder {
    String encode(final String rawPassword);
    boolean matches(final String rawPassword, final String encodedPassword);
}
