package blaunch.bladmin.repository.condition;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.util.StringUtils;

import static blaunch.bladmin.entity.QComment.comment;

public class CommentRepositoryCondition {
    public static BooleanExpression typeIdEq(String typeId){
        return StringUtils.hasText(typeId) ? comment.typeId.eq(typeId) : null;
    }
}
