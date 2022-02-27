package blaunch.bladmin.repository.custom;

import blaunch.bladmin.dto.GuideDto;
import blaunch.bladmin.dto.condition.GuideCondition;
import blaunch.bladmin.entity.guide.Guide;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GuideRepositoryCustom {
    Page<GuideDto.FindGuidesRes> findGuides(GuideCondition condition, Pageable pageable);
    Optional<GuideDto.FindGuideByIdRes> findGuideById(GuideCondition condition);
}
