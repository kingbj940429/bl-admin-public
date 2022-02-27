package blaunch.bladmin.service;

import blaunch.bladmin.dto.GuideDto;
import blaunch.bladmin.entity.Account;
import blaunch.bladmin.entity.guide.Guide;
import blaunch.bladmin.entity.status.ActiveStatus;
import blaunch.bladmin.exception.ErrorCode;
import blaunch.bladmin.exception.custom.AccountNotValidException;
import blaunch.bladmin.exception.custom.GuideNotFoundException;
import blaunch.bladmin.repository.AccountRepository;
import blaunch.bladmin.repository.GuideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GuideService {
    private final GuideRepository guideRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public Guide create(GuideDto.InsGuide dto){
        Account findAccount = accountRepository.findById(dto.getAccount().getId()).orElseThrow(() -> new AccountNotValidException(ErrorCode.ACCOUNT_NOT_VALID));
        GuideDto.InsGuide insGuide = GuideDto.InsGuide.builder()
                .type(dto.getType()).title(dto.getTitle()).subTitle(dto.getSubTitle()).contents(dto.getContents()).imgLink(dto.getImgLink())
                .metaKeyword(dto.getMetaKeyword()).metaTitle(dto.getMetaTitle()).metaDesc(dto.getMetaDesc())
                .ogTitle(dto.getOgTitle()).ogDesc(dto.getOgDesc()).ogImg(dto.getOgImg())
                .account(findAccount).activeStatus(ActiveStatus.ENABLE)
                .build();

        return guideRepository.save(insGuide.toEntity());
    }

    @Transactional
    public GuideDto.UpdActiveStatus updActiveStatus(String guideId, GuideDto.UpdActiveStatus dto){
        Guide findGuide = guideRepository.findById(guideId).orElseThrow(() -> new GuideNotFoundException(ErrorCode.GUIDE_NOT_FOUND));
        findGuide.updActiveStatus(dto.getActiveStatus());

        return dto;
    }

    @Transactional
    public GuideDto.UpdGuide updGuide(String guideId, GuideDto.UpdGuide dto){
        Guide findGuide = guideRepository.findById(guideId).orElseThrow(() -> new GuideNotFoundException(ErrorCode.GUIDE_NOT_FOUND));

        findGuide.updGuide(dto.getType(), dto.getTitle(), dto.getSubTitle(), dto.getContents(),
                dto.getMetaKeyword(),dto.getMetaTitle(),dto.getMetaDesc(),
                dto.getOgTitle(),dto.getOgDesc(),dto.getOgImg(), dto.getImgLink(),
                dto.getActiveStatus());

        return dto;
    }

    @Transactional
    public String delete(String guideId){
        Guide findGuide = guideRepository.findById(guideId).orElseThrow(() -> new GuideNotFoundException(ErrorCode.GUIDE_NOT_FOUND));
        guideRepository.delete(findGuide);

        return guideId;
    }
}
