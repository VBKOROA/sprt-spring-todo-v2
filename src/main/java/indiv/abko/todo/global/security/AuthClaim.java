package indiv.abko.todo.global.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthClaim {
    MEMBER_ID("memberId");

    private final String key;
}
