package blaunch.bladmin.service;

import blaunch.bladmin.dto.condition.AccountCondition;
import blaunch.bladmin.entity.Account;
import blaunch.bladmin.entity.AccountAuthority;
import blaunch.bladmin.entity.status.PlatformStatus;
import blaunch.bladmin.exception.ErrorCode;
import blaunch.bladmin.repository.AccountAuthorityRepository;
import blaunch.bladmin.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountAuthorityRepository accountAuthorityRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String userId) {

        return accountRepository.findOneByUserId(userId)
                .map(account -> createUser(account))
                .orElseThrow(() -> new UsernameNotFoundException(ErrorCode.ACCOUNT_NOT_FOUND.getMessage()));
    }

    private org.springframework.security.core.userdetails.User createUser(Account account) {
        SimpleGrantedAuthority grantedAuthority = accountAuthorityRepository.findOneByAccountId(account.getId())
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority().getAuthorityName()))
                .orElseThrow(RuntimeException::new);

        return new org.springframework.security.core.userdetails.User(account.getUserId(),
                account.getUserPassword(),//passwordEncoder 방식으로 넣어줘야함.
                Arrays.asList(grantedAuthority)); //grantedAuthorities 넣어줘야함.
    }
}

