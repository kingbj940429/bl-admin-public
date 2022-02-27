package blaunch.bladmin.repository.condition;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.util.StringUtils;

import static blaunch.bladmin.entity.guide.QGuide.guide;


public class GuideRepositoryCondition {
    public static BooleanExpression idEq(String id){
        return StringUtils.hasText(id) ? guide.id.eq(id) : null;
    }
}
