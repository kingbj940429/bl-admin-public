package blaunch.bladmin.dto;

import blaunch.bladmin.entity.Account;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AccountTokenDto {
    /**
     * 토큰 응답
     */
    public static class AccountTokenRes {
        private String userId;
        private String userPassword;

        public AccountTokenRes(Account account) {
            this.userId = account.getUserId();
            this.userPassword = account.getUserPassword();
        }
    }
}
