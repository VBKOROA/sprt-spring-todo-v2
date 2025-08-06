package indiv.abko.todo.member.adapter.out.persistence.entity;

import indiv.abko.todo.global.entity.BaseTimeJpaEntity;
import indiv.abko.todo.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberJpaEntity extends BaseTimeJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // 이름

    private String email; // 이메일 (로그인용 ID)

    private String password; // 패스워드

    public Member toDomainForPublic() {
        return Member.builder()
                .name(this.name)
                .email(this.email)
                .modifiedAt(getModifiedAt())
                .createdAt(getCreatedAt())
                .build();
    }
}
