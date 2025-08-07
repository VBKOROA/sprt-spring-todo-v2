package indiv.abko.todo.todo.adapter.out.persistence;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.jpa.impl.JPAQueryFactory;
import indiv.abko.todo.todo.adapter.out.persistence.mapper.TodoEntityMapper;
import indiv.abko.todo.todo.domain.SearchTodosCriteria;
import indiv.abko.todo.todo.domain.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import static indiv.abko.todo.todo.adapter.out.persistence.entity.QTodoJpaEntity.todoJpaEntity;

@Repository
@RequiredArgsConstructor
public class TodoQDSLRepository {
    private static final String ORDER_SEPARATOR = "_";
    private static final int PROPERTY_IDX = 0;
    private static final int ORDER_IDX = 1;
    private static final int VALID_SORT_CONDITION_LENGTH = 2;
    private static final OrderSpecifier<?> DEFAULT_ORDER = new OrderSpecifier<>(Order.DESC, todoJpaEntity.modifiedAt);

    private final JPAQueryFactory queryFactory;
    private final TodoEntityMapper todoEntityMapper;

    public Page<Todo> search(final SearchTodosCriteria searchCriteria, final Pageable pageable) {
        final var authorExpression = authorNameLike(searchCriteria.authorName());
        final var titleExpression = titleLike(searchCriteria.title());
        final var contentExpression = contentLike(searchCriteria.content());

        final var content = queryFactory
                .selectFrom(todoJpaEntity)
                .where(
                        authorExpression,
                        titleExpression,
                        contentExpression
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderBy(searchCriteria.orderBy()))
                .fetch();

        final var countQuery = queryFactory
                .select(todoJpaEntity.count())
                .from(todoJpaEntity)
                .where(
                        authorExpression,
                        titleExpression,
                        contentExpression
                );

        var page = PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
        return page.map(todoEntityMapper::toDomain);
    }

    private static String makeLikePattern(final String value) {
        return "%" + value + "%";
    }

    private BooleanExpression authorNameLike(final String authorName) {
        return StringUtils.hasText(authorName) ? todoJpaEntity.authorName.like(makeLikePattern(authorName)) : null;
    }

    private BooleanExpression titleLike(final String title) {
        return StringUtils.hasText(title) ? todoJpaEntity.title.like(makeLikePattern(title)) : null;
    }

    private BooleanExpression contentLike(final String content) {
        return StringUtils.hasText(content) ? todoJpaEntity.content.like(makeLikePattern(content)) : null;
    }

    private OrderSpecifier<?> getOrderBy(final String sort) {
        if (!StringUtils.hasText(sort)) {
            return DEFAULT_ORDER;
        }

        final String[] sortCondition = sort.split(ORDER_SEPARATOR);
        if (sortCondition.length != VALID_SORT_CONDITION_LENGTH) {
            return DEFAULT_ORDER;
        }

        final String property = sortCondition[PROPERTY_IDX];
        final ComparableExpressionBase<?> path = getPath(property);

        if (path == null) {
            return DEFAULT_ORDER;
        }

        final Order direction = "desc".equalsIgnoreCase(sortCondition[ORDER_IDX]) ?
                Order.DESC : Order.ASC;

        return new OrderSpecifier<>(direction, path);
    }

    private ComparableExpressionBase<?> getPath(final String property) {
        return switch (property) {
            case "id" -> todoJpaEntity.id;
            case "title" -> todoJpaEntity.title;
            case "content" -> todoJpaEntity.content;
            case "authorName" -> todoJpaEntity.authorName;
            case "createdAt" -> todoJpaEntity.createdAt;
            case "modifiedAt" -> todoJpaEntity.modifiedAt;
            default -> null;
        };
    }
}