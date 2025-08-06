package indiv.abko.todo.member.domain;

import indiv.abko.todo.member.domain.vo.EmailVO;
import indiv.abko.todo.member.domain.vo.EncodedPasswordVO;
import indiv.abko.todo.member.domain.vo.NameVO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {
    private Long id;
    private NameVO name; // 이름
    private EmailVO email; // 이메일 (로그인용 ID)
    private LocalDateTime modifiedAt; // 수정 시간
    private LocalDateTime createdAt; // 생성 시간
    private EncodedPasswordVO password;
}
