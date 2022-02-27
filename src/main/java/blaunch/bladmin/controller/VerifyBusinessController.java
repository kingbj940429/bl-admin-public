package blaunch.bladmin.controller;

import blaunch.bladmin.dto.ResponseDto;
import blaunch.bladmin.dto.VerifyBusinessDto;
import blaunch.bladmin.service.VerifyBusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class VerifyBusinessController {
    private final VerifyBusinessService verifyBusinessService;

    @PostMapping("/businesses/{businessId}/verify")
    public ResponseDto.ResultList<VerifyBusinessDto.insVerifyBusinessRes> create(@RequestBody List<VerifyBusinessDto.insVerifyBusinessRes> insVerifyBusinessesRes,
                              @PathVariable String businessId){
        return new ResponseDto.ResultList(verifyBusinessService.create(insVerifyBusinessesRes, businessId));
    }
}
