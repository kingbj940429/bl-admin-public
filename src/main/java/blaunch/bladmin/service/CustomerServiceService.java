package blaunch.bladmin.service;

import blaunch.bladmin.dto.CustomerServiceDto;
import blaunch.bladmin.entity.Account;
import blaunch.bladmin.entity.CustomerService;
import blaunch.bladmin.entity.guide.Guide;
import blaunch.bladmin.entity.status.ActiveStatus;
import blaunch.bladmin.entity.status.CustomerServiceKind;
import blaunch.bladmin.exception.ErrorCode;
import blaunch.bladmin.exception.custom.AccountNotValidException;
import blaunch.bladmin.exception.custom.CustomerServiceNotFoundException;
import blaunch.bladmin.exception.custom.GuideNotFoundException;
import blaunch.bladmin.repository.AccountRepository;
import blaunch.bladmin.repository.CustomerServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerServiceService {
    private final CustomerServiceRepository customerServiceRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public CustomerService createNotice(CustomerServiceDto.InsNotice dto){
        Account findAccount = accountRepository.findById(dto.getAccount().getId()).orElseThrow(() -> new AccountNotValidException(ErrorCode.ACCOUNT_NOT_VALID));
        CustomerServiceDto.InsNotice insNotice = CustomerServiceDto.InsNotice.builder()
                .csTitle(dto.getCsTitle())
                .csContents(dto.getCsContents())
                .csKind(CustomerServiceKind.A)
                .csType(dto.getCsType())
                .account(findAccount)
                .activeStatus(ActiveStatus.ENABLE)
                .build();

        return customerServiceRepository.save(insNotice.toEntity());
    }

    @Transactional
    public CustomerServiceDto.UpdAnswerStatus updAnswerStatus(String csId, CustomerServiceDto.UpdAnswerStatus dto){
        CustomerService findCs = customerServiceRepository.findById(csId).orElseThrow(() -> new CustomerServiceNotFoundException(ErrorCode.CS_NOT_FOUND));
        findCs.updAnswerStatus(dto.getAnswerStatus());
        return dto;
    }

    @Transactional
    public CustomerServiceDto.UpdNotice updNotice(String csId, CustomerServiceDto.UpdNotice dto){
        CustomerService findCs = customerServiceRepository.findById(csId).orElseThrow(() -> new CustomerServiceNotFoundException(ErrorCode.CS_NOT_FOUND));

        findCs.updNotice(dto.getCsTitle(), dto.getCsContents());

        return dto;
    }

    @Transactional
    public String delete(String csId){
        CustomerService findCs = customerServiceRepository.findById(csId).orElseThrow(() -> new CustomerServiceNotFoundException(ErrorCode.CS_NOT_FOUND));
        customerServiceRepository.delete(findCs);

        return csId;
    }
}
