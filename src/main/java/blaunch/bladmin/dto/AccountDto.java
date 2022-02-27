package blaunch.bladmin.dto;

import blaunch.bladmin.entity.Account;
import blaunch.bladmin.entity.status.PlatformStatus;
import blaunch.bladmin.entity.status.UserStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AccountDto {

    @ToString
    @Getter
    public static class FindAccountsRes {
        private String id;
        private PlatformStatus platform;
        private String userId;
        private String userName;
        private String nickname;
        private UserStatus userStatus;

        @QueryProjection
        public FindAccountsRes(Account account) {
            this.id = account.getId();
            this.platform = account.getPlatformStatus();
            this.userId = account.getUserId();
            this.userName = account.getUserName();
            this.nickname = account.getNickname();
            this.userStatus = account.getUserStatus();
        }
    }

    @Getter
    public static class FindAccountRes {
        private String id;
        private PlatformStatus platformStatus;
        private String userId;
        private String nickname;
        private String userName;
//        private String 사업자인증;
        private String userPhone;
        private LocalDateTime createdDate;
        private String address;
        private UserStatus userStatus;
        private int boardCount;

        private String introduce;
        private String job;
        private String activeField;
        private String marketingPushYn;
        private String userEmail;

        public FindAccountRes(Account account) {
            this.id = account.getId();
            this.platformStatus = account.getPlatformStatus();
            this.userId = account.getUserId();
            this.nickname = account.getNickname();
            this.userName = account.getUserName();
            this.userPhone = account.getUserPhone();
            this.createdDate = account.getCreatedDate();
            this.address = account.getAddress();
            this.userStatus = account.getUserStatus();
            this.boardCount = account.getBoardCount();
            this.introduce = account.getIntroduce();
            this.job = account.getJob();
            this.activeField = account.getActiveField();
            this.marketingPushYn = account.getPushEmbed().getMarketingPushYn();
            this.userEmail = account.getUserEmail();
        }
    }

    /**
     * 회원 가입
     */
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class insAccount {
        private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();// DIP, OCP 위배됨

        private PlatformStatus platform;
        @NotBlank(message = "userId은 Null 일 수 없습니다.")
        private String userId;
        private String nickname;
        @NotBlank(message = "userName은 Null 일 수 없습니다.")
        private String userName;
        private String userPhone;
        @NotBlank(message = "userPassword은 Null 일 수 없습니다.")
        private String userPassword;
        @Email
        private String userEmail;
        @NotNull(message = "userStatus은 Null 일 수 없습니다.")
        private UserStatus userStatus;

        @Builder
        public insAccount(PlatformStatus platform, String userId, String nickname, String userName, String userPhone, String userPassword, String userEmail, UserStatus userStatus) {
            this.platform = platform;
            this.userId = userId;
            this.nickname = nickname;
            this.userName = userName;
            this.userPhone = userPhone;
            this.userPassword = userPassword;
            this.userEmail = userEmail;
            this.userStatus = userStatus;
        }

        public Account toEntity(){
            return Account.builder()
                    .platformStatus(this.platform)
                    .userId(this.userId)
                    .nickname(this.nickname)
                    .userName(this.userName)
                    .userPhone(this.userPhone)
                    .userPassword(encoder.encode(this.userPassword))
                    .userEmail(this.userEmail)
                    .userStatus(this.userStatus)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class updAccountUserStatus {
        @NotNull(message = "userStatus은 Null 일 수 없습니다.")
        private UserStatus userStatus;

        @Builder
        public updAccountUserStatus(UserStatus userStatus) {
            this.userStatus = userStatus;
        }
    }

    /**
     * 사용자 관리 - 사용자 리스트
     */
    @Getter
    public static class AccountManageRes{
        private String id;
        private PlatformStatus platformStatus;
        private String userId;
        private String userName;
        private String nickname;
        private UserStatus userStatus;

        public AccountManageRes(Account account) {
            this.id = account.getId();
            this.platformStatus = account.getPlatformStatus();
            this.userId = account.getUserId();
            this.userName = account.getUserName();
            this.nickname = account.getNickname();
            this.userStatus = account.getUserStatus();
        }
    }

    /**
     * Account에 대한 공통 Res Dto
     */
    @Getter
    public static class Res {
        private PlatformStatus platform;
        private String userId;
        private String nickname;
        private String userName;
        private String userEmail;

        public Res(Account account){
            this.platform = account.getPlatformStatus();
            this.userId = account.getUserId();
            this.nickname = account.getNickname();
            this.userName = account.getUserName();
            this.userEmail = account.getUserEmail();
        }
    }
}
