package blaunch.bladmin.dto;

import blaunch.bladmin.entity.Account;
import blaunch.bladmin.entity.AccountAuthority;
import blaunch.bladmin.entity.Authority;
import blaunch.bladmin.entity.status.PlatformStatus;
import lombok.*;

public class AccountAuthorityDto {

    @ToString
    @Getter
    public static class findOneByAccountAuthorityDto{
        private PlatformStatus platformStatus;
        private String userId;
        private String userName;
        private String userEmail;
        private String authorityName;

        @Builder
        public findOneByAccountAuthorityDto(Account account, Authority authority) {
            this.platformStatus = account.getPlatformStatus();
            this.userId = account.getUserId();
            this.userName = account.getUserName();
            this.userEmail = account.getUserEmail();
            this.authorityName = authority.getAuthorityName();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class insAccountAuthority{
        private Account account;
        private Authority authority;

        @Builder
        public insAccountAuthority(Account account, Authority authority) {
            this.account = account;
            this.authority = authority;
        }

        public AccountAuthority toEntity(){
            return AccountAuthority.builder()
                    .account(account)
                    .authority(authority)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Res{
        private String userId;
        private String authorityName;

        public Res(Account account, Authority authority){
            this.userId = account.getUserId();
            this.authorityName = authority.getAuthorityName();
        }
    }
}
