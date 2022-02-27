package blaunch.bladmin.service;

import blaunch.bladmin.entity.Token;
import blaunch.bladmin.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    @Transactional
    public Token create(Token token){
        return tokenRepository.save(token);
    }

    @Transactional
    public Token updateToken(String id, Token token) {
        Token findToken = tokenRepository.findByAccount_Id(id);
        findToken.updateToken(token);
        return findToken;
    }
}
