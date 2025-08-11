package indiv.abko.todo.global.vo;

import lombok.*;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorVO {
    private Long id;
    private String name;

    public static AuthorVO reconstitute(long id, String name) {
        return AuthorVO.builder()
                .id(id)
                .name(name)
                .build();
    }
}
