package blaunch.bladmin.controller;

import blaunch.bladmin.dto.AccountDto;
import blaunch.bladmin.dto.AuthenticateDto;
import blaunch.bladmin.dto.TokenDto;
import blaunch.bladmin.dto.AccountAuthorityDto;
import blaunch.bladmin.dto.condition.AccountCondition;
import blaunch.bladmin.entity.Account;
import blaunch.bladmin.entity.AccountAuthority;
import blaunch.bladmin.jwt.JwtFilter;
import blaunch.bladmin.repository.AccountRepository;
import blaunch.bladmin.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AccountRepository accountRepository;

    @PostMapping("/authenticate")
    public ResponseEntity<TokenDto> authenticate(@RequestBody AuthenticateDto.InsAuthenticate dto) {
        authService.authorize(dto);//권한 부여
        String jwt = authService.authenticate(dto);//토큰 생성

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }

}
