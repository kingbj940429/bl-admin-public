package blaunch.bladmin.controller;

import blaunch.bladmin.dto.CustomerServiceDto;
import blaunch.bladmin.dto.ResponseDto;
import blaunch.bladmin.dto.condition.CustomerServiceCondition;
import blaunch.bladmin.entity.CustomerService;
import blaunch.bladmin.entity.status.CustomerServiceKind;
import blaunch.bladmin.exception.ErrorCode;
import blaunch.bladmin.exception.custom.CustomerServiceNotFoundException;
import blaunch.bladmin.repository.CustomerServiceRepository;
import blaunch.bladmin.service.CustomerServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CustomerServiceController {
    private final CustomerServiceRepository customerServiceRepository;
    private final CustomerServiceService customerServiceService;

    @GetMapping("/cs/notices")
    public Page<CustomerServiceDto.FindNoticesRes> findNotices(@PageableDefault(page = 0, size = 10)Pageable pageable){
        CustomerServiceCondition condition = CustomerServiceCondition.builder().csKind(CustomerServiceKind.A).build();
        Page<CustomerService> findCss = customerServiceRepository.findCss(condition, pageable);

        List<CustomerServiceDto.FindNoticesRes> findNoticesRes = findCss.getContent().stream()
                .map(customerService -> new CustomerServiceDto.FindNoticesRes(customerService))
                .collect(toList());

        return new PageImpl(findNoticesRes, pageable, findCss.getTotalElements());
    }

    @GetMapping("/cs/notices/{csId}")
    public ResponseDto.Single<CustomerServiceDto.FindNoticeByIdRes> findNoticeById(@PathVariable("csId") String id){
        CustomerServiceDto.FindNoticeByIdRes findNoticeByIdRes = customerServiceRepository.findById(id)
                .map(customerService -> new CustomerServiceDto.FindNoticeByIdRes(customerService))
                .orElseThrow(() -> new CustomerServiceNotFoundException(ErrorCode.CS_NOT_FOUND));

        return new ResponseDto.Single(findNoticeByIdRes);
    }

    @GetMapping("/cs/inquiries")
    public Page<CustomerServiceDto.FindInquiriesRes> findInquiries(@PageableDefault(page = 0, size = 10)Pageable pageable){
        CustomerServiceCondition condition = CustomerServiceCondition.builder().csKind(CustomerServiceKind.I).build();
        Page<CustomerService> findCss = customerServiceRepository.findCss(condition, pageable);

        List<CustomerServiceDto.FindInquiriesRes> findInquiriesRes = findCss.getContent().stream()
                .map(customerService -> new CustomerServiceDto.FindInquiriesRes(customerService))
                .collect(toList());

        return new PageImpl(findInquiriesRes, pageable, findCss.getTotalElements());
    }

    @GetMapping("/cs/inquiries/{csId}")
    public ResponseDto.Single<CustomerServiceDto.FindInquiryByIdRes> findInquiryById(@PathVariable("csId") String id){
        CustomerServiceDto.FindInquiryByIdRes findInquiryByIdRes = customerServiceRepository.findById(id)
                .map(customerService -> new CustomerServiceDto.FindInquiryByIdRes(customerService))
                .orElseThrow(() -> new CustomerServiceNotFoundException(ErrorCode.CS_NOT_FOUND));

        return new ResponseDto.Single(findInquiryByIdRes);
    }

    @GetMapping("/cs/reports")
    public Page<CustomerServiceDto.FindReportsRes> findReports(@PageableDefault(page = 0, size = 10)Pageable pageable){
        CustomerServiceCondition condition = CustomerServiceCondition.builder().csKind(CustomerServiceKind.R).build();
        Page<CustomerService> findCss = customerServiceRepository.findCss(condition, pageable);

        List<CustomerServiceDto.FindReportsRes> findReportsRes = findCss.getContent().stream()
                .map(customerService -> new CustomerServiceDto.FindReportsRes(customerService))
                .collect(toList());

        return new PageImpl(findReportsRes, pageable, findCss.getTotalElements());
    }

    @GetMapping("/cs/reports/{csId}")
    public ResponseDto.Single<CustomerServiceDto.FindReportByIdRes> findReportById(@PathVariable("csId") String id){
        CustomerServiceDto.FindReportByIdRes findReportByIdRes = customerServiceRepository.findById(id)
                .map(customerService -> new CustomerServiceDto.FindReportByIdRes(customerService))
                .orElseThrow(() -> new CustomerServiceNotFoundException(ErrorCode.CS_NOT_FOUND));

        return new ResponseDto.Single(findReportByIdRes);
    }

    @PostMapping("/cs/notices")
    public ResponseDto.Single<CustomerServiceDto.InsNoticeRes> create(@RequestBody CustomerServiceDto.InsNotice insNotice){
        CustomerServiceDto.InsNoticeRes insNoticeRes = new CustomerServiceDto.InsNoticeRes(customerServiceService.createNotice(insNotice));
        return new ResponseDto.Single(insNoticeRes);
    }

    @PutMapping("/cs/notices/{csId}")
    public ResponseDto.Single<CustomerServiceDto.UpdNotice> updNotice(@RequestBody CustomerServiceDto.UpdNotice updNotice,
                                                                      @PathVariable("csId") String id){
        return new ResponseDto.Single<>(customerServiceService.updNotice(id, updNotice));
    }

    @PutMapping("/cs/reports/{csId}/answer-status")
    public ResponseDto.Single<CustomerServiceDto.UpdAnswerStatus> updReportAnswerStatus(@RequestBody CustomerServiceDto.UpdAnswerStatus updAnswerStatus,
                                                                                        @PathVariable("csId") String id){
        return new ResponseDto.Single(customerServiceService.updAnswerStatus(id, updAnswerStatus));
    }

    @DeleteMapping("/cs/{csId}")
    public ResponseDto.Single<String> delete(@PathVariable("csId") String id){
        return new ResponseDto.Single(customerServiceService.delete(id));
    }
}
