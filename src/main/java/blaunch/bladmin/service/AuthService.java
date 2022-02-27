package blaunch.bladmin.service;

import blaunch.bladmin.dto.AccountAuthorityDto;
import blaunch.bladmin.dto.AuthenticateDto;
import blaunch.bladmin.entity.Account;
import blaunch.bladmin.entity.AccountAuthority;
import blaunch.bladmin.entity.Authority;
import blaunch.bladmin.entity.Token;
import blaunch.bladmin.entity.status.PlatformStatus;
import blaunch.bladmin.exception.ErrorCode;
import blaunch.bladmin.exception.custom.AccountNotValidException;
import blaunch.bladmin.exception.custom.AuthorityNotFoundException;
import blaunch.bladmin.jwt.TokenProvider;
import blaunch.bladmin.repository.AccountAuthorityRepository;
import blaunch.bladmin.repository.AccountRepository;
import blaunch.bladmin.repository.AuthorityRepository;
import blaunch.bladmin.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    private final AccountRepository accountRepository;
    private final TokenRepository tokenRepository;
    private final AuthorityRepository authorityRepository;
    private final AccountAuthorityRepository accountAuthorityRepository;

    /**
     * /api/authenticate 요청을 처리하는 authorize 메소드는
     *
     * username, password를 파라미터로 받아서 UsernamePasswordAuthenticationToken 객체를 생성합니다.
     *
     * 해당 객체를 통해 authenticate 메소드 로직을 수행합니다. 이때 위에서 만들었던 loadUserByUsername 메소드가 수행되며 유저 정보를 조회해서 인증 정보를 생성하게 됩니다.
     *
     * 해당 인증 정보를 JwtFilter 클래스의 doFilter 메소드와 유사하게 현재 실행중인 스레드 ( Security Context ) 에 저장합니다.
     *
     * 또한 해당 인증 정보를 기반으로 TokenProvider의 createToken 메소드를 통해 jwt 토큰을 생성합니다.
     *
     * 생성된 Token을 Response Header에 넣고, TokenDto 객체를 이용해 Reponse Body에도 넣어서 리턴합니다.
     * @return
     */
    @Transactional
    public String authenticate(AuthenticateDto.InsAuthenticate dto) {
        Account findAccount = accountRepository.findOneByUserIdAndPlatformStatus(dto.getUserId(), PlatformStatus.X)
                .orElseThrow(() -> new AccountNotValidException(ErrorCode.ACCOUNT_NOT_VALID));

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(findAccount.getUserId(), dto.getUserPassword());//raw password 넣어줘야함

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);
        Token token = Token.builder().name(jwt).account(findAccount).build();
        Token findToken = tokenRepository.findByAccount_Id(findAccount.getId());

        if(findToken != null){
            tokenService.updateToken(findAccount.getId(), token);
        }else{
            tokenService.create(token);
        }

        return jwt;
    }

    @Transactional
    public void authorize(AuthenticateDto.InsAuthenticate dto) {
        //계정을 찾는다.
        Account findAccount = accountRepository
                .findOneByUserId(dto.getUserId())
                .orElseThrow(() -> new AccountNotValidException(ErrorCode.ACCOUNT_NOT_VALID));

        //비밀번호를 확인하다.
        boolean equals = passwordEncoder.matches(dto.getUserPassword(), findAccount.getUserPassword());
        if(!equals) throw new AccountNotValidException(ErrorCode.ACCOUNT_NOT_VALID);

        //계정 키를 통해 권한을 찾는다.
        Optional<AccountAuthority> findAccountAuthority = accountAuthorityRepository.findOneByAccountId(findAccount.getId());

        //계정의 권한을 업데이트하기 위하여 디비에서 조회한다.
        Authority findAuthority = authorityRepository.findOneByAuthorityName(dto.getAuthorityName()).orElseThrow(() ->  new AuthorityNotFoundException(ErrorCode.ROLE_NOT_FOUND));

        if (!findAccountAuthority.isPresent()) {
            AccountAuthorityDto.insAccountAuthority accountAuthority = AccountAuthorityDto.insAccountAuthority.builder()
                    .account(findAccount)
                    .authority(findAuthority)
                    .build();

            accountAuthorityRepository.save(accountAuthority.toEntity());
        } else {
            findAccountAuthority.get().updateAuthority(findAuthority);
        }
    }
}
