package indiv.abko.todo.todo.adapter.out.persistence.entity;

import indiv.abko.todo.global.entity.BaseTimeJpaEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "todo")
public class TodoJpaEntity extends BaseTimeJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; // 일정 제목

    private String content; // 일정 내용

    private Long authorId; // 작성자 고유키

    private String authorName; // 작성자 이름
}
