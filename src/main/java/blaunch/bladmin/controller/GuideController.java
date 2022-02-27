package blaunch.bladmin.controller;

import blaunch.bladmin.dto.GuideDto;
import blaunch.bladmin.dto.ResponseDto;
import blaunch.bladmin.dto.condition.GuideCondition;
import blaunch.bladmin.exception.ErrorCode;
import blaunch.bladmin.exception.custom.GuideNotFoundException;
import blaunch.bladmin.repository.GuideRepository;
import blaunch.bladmin.service.GuideService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class GuideController {
    private final GuideService guideService;
    private final GuideRepository guideRepository;

    @GetMapping("/guides")
    public Page<GuideDto.FindGuidesRes> findGuides(@PageableDefault(page = 0, size = 10) Pageable pageable){
        GuideCondition condition = GuideCondition.builder().build();
        return guideRepository.findGuides(condition, pageable);
    }

    @GetMapping("/guides/{guideId}")
    public ResponseDto.Single<GuideDto.FindGuideByIdRes> findGuideById(@PathVariable("guideId") String id){
        GuideCondition condition = GuideCondition.builder().id(id).build();
        GuideDto.FindGuideByIdRes findGuideByIdRes = guideRepository.findGuideById(condition).orElseThrow(() -> new GuideNotFoundException(ErrorCode.GUIDE_NOT_FOUND));

        return new ResponseDto.Single(findGuideByIdRes);
    }

    @PostMapping("/guides")
    public ResponseDto.Single<GuideDto.InsGuideRes> create(@RequestBody GuideDto.InsGuide insGuide){
        GuideDto.InsGuideRes insGuideRes = new GuideDto.InsGuideRes(guideService.create(insGuide));
        return new ResponseDto.Single(insGuideRes);
    }

    @PutMapping("/guides/{guideId}/active-status")
    public ResponseDto.Single<GuideDto.UpdActiveStatus> updActiveStatus(@RequestBody GuideDto.UpdActiveStatus updActiveStatus,
                                                                        @PathVariable("guideId") String id){
        return new ResponseDto.Single(guideService.updActiveStatus(id ,updActiveStatus));
    }

    @PutMapping("/guides/{guideId}")
    public ResponseDto.Single<GuideDto.UpdGuide> updGuide(@RequestBody GuideDto.UpdGuide updGuide,
                                                          @PathVariable("guideId") String id){
        return new ResponseDto.Single(guideService.updGuide(id, updGuide));
    }

    @DeleteMapping("/guides/{guideId}")
    public ResponseDto.Single<String> delete(@PathVariable("guideId") String id){
        return new ResponseDto.Single(guideService.delete(id));
    }
}
