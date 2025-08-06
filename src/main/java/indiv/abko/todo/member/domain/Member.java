package indiv.abko.todo.member.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {
    private String name; // 이름
    private String email; // 이메일 (로그인용 ID)
    private LocalDateTime modifiedAt; // 수정 시간
    private LocalDateTime createdAt; // 생성 시간
    private String password;
}
