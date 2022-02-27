package blaunch.bladmin.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthenticateDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class InsAuthenticate {
        private String userId;
        private String userPassword;
        private String authorityName;

        @Builder
        public InsAuthenticate(String userId, String userPassword, String authorityName) {
            this.userId = userId;
            this.userPassword = userPassword;
            this.authorityName = authorityName;
        }

    }
}
