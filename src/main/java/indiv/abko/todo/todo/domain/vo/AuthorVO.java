package indiv.abko.todo.todo.domain.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorVO {
    private Long id;
    private String name;

    public static AuthorVO reconstitute(Long id, String name) {
        return AuthorVO.builder()
                .id(id)
                .name(name)
                .build();
    }
}
