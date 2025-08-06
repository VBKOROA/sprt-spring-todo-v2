package indiv.abko.todo.member.adapter.out.persistence.entity;

import indiv.abko.todo.global.entity.BaseTimeJpaEntity;
import indiv.abko.todo.member.domain.Member;
import indiv.abko.todo.member.domain.vo.EmailVO;
import indiv.abko.todo.member.domain.vo.EncodedPasswordVO;
import indiv.abko.todo.member.domain.vo.NameVO;
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

    @Column(unique = true)
    private String email; // 이메일 (로그인용 ID)

    private String password; // 패스워드

    public static MemberJpaEntity fromDomain(Member member) {
        return MemberJpaEntity.builder()
                .id(member.getId())
                .email(member.getEmail().getValue())
                .name(member.getName().getValue())
                .password(member.getPassword().getValue())
                .createdAt(member.getCreatedAt())
                .modifiedAt(member.getModifiedAt())
                .build();
    }

    public Member toDomain() {
        return Member.builder()
                .id(id)
                .password(EncodedPasswordVO.reconstitute(password))
                .name(NameVO.reconstitute(this.name))
                .email(EmailVO.reconstitute(this.email))
                .modifiedAt(getModifiedAt())
                .createdAt(getCreatedAt())
                .build();
    }
}
