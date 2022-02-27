package blaunch.bladmin.dto;

import blaunch.bladmin.entity.Account;
import blaunch.bladmin.entity.AnswerStatus;
import blaunch.bladmin.entity.CustomerService;
import blaunch.bladmin.entity.status.ActiveStatus;
import blaunch.bladmin.entity.status.CustomerServiceKind;
import blaunch.bladmin.entity.status.PlatformStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CustomerServiceDto {

    @Getter
    public static class FindNoticesRes {
        private String id;
        private String csTitle;
        private String csContents;
        private LocalDateTime createdDate;
        private ActiveStatus activeStatus;

        public FindNoticesRes(CustomerService cs) {
            this.id = cs.getId();
            this.csTitle = cs.getCsTitle();
            this.csContents = cs.getCsContents();
            this.createdDate = cs.getCreatedDate();
            this.activeStatus = cs.getActiveStatus();
        }
    }

    @Getter
    public static class FindNoticeByIdRes {
        private String id;
        private String csTitle;
        private String csContents;

        public FindNoticeByIdRes(CustomerService cs) {
            this.id = cs.getId();
            this.csTitle = cs.getCsTitle();
            this.csContents = cs.getCsContents();
        }
    }

    @Getter
    public static class FindInquiriesRes {
        private String id;
        private String csType;
        private String csContents;
        private LocalDateTime createdDate;
        private String nickname;
        private AnswerStatus answerStatus;

        public FindInquiriesRes(CustomerService cs) {
            this.id = cs.getId();
            this.csType = cs.getCsType();
            this.csContents = cs.getCsContents();
            this.nickname = cs.getAccount().getNickname();
            this.createdDate = cs.getCreatedDate();
            this.answerStatus = cs.getAnswerStatus();
        }
    }

    @Getter
    public static class FindInquiryByIdRes {
        private String id;
        private String accountId;
        private PlatformStatus platformStatus;
        private String userId;
        private String nickname;
        private String userName;
        private String userPhone;
        private String csType;
        private String csContents;
        private LocalDateTime accountCreatedDate;
        private LocalDateTime createdDate;
        private AnswerStatus answerStatus;

        public FindInquiryByIdRes(CustomerService cs) {
            this.id = cs.getId();
            this.accountId = cs.getAccount().getId();
            this.platformStatus = cs.getAccount().getPlatformStatus();
            this.userId = cs.getAccount().getUserId();
            this.nickname = cs.getAccount().getNickname();
            this.userName = cs.getAccount().getUserName();
            this.userPhone = cs.getAccount().getUserPhone();
            this.csType = cs.getCsType();
            this.csContents = cs.getCsContents();
            this.accountCreatedDate = cs.getAccount().getCreatedDate();
            this.createdDate = cs.getCreatedDate();
            this.answerStatus = cs.getAnswerStatus();
        }
    }

    @Getter
    public static class FindReportsRes {
        private String id;
        private String csType;
        private String csContents;
        private LocalDateTime createdDate;
        private String nickname;
        private AnswerStatus answerStatus;

        public FindReportsRes(CustomerService cs) {
            this.id = cs.getId();
            this.csType = cs.getCsType();
            this.csContents = cs.getCsContents();
            this.nickname = cs.getAccount().getNickname();
            this.createdDate = cs.getCreatedDate();
            this.answerStatus = cs.getAnswerStatus();
        }
    }

    @Getter
    public static class FindReportByIdRes {
        private String id;
        private String accountId;
        private String userId;
        private String nickname;
        private String userEmail;
        private String csType;
        private String csContents;
        private LocalDateTime createdDate;
        private AnswerStatus answerStatus;

        public FindReportByIdRes(CustomerService cs) {
            this.id = cs.getId();
            this.accountId = cs.getAccount().getId();
            this.userId = cs.getAccount().getUserId();
            this.nickname = cs.getAccount().getNickname();
            this.userEmail = cs.getAccount().getUserEmail();
            this.csType = cs.getCsType();
            this.csContents = cs.getCsContents();
            this.createdDate = cs.getCreatedDate();
            this.answerStatus = cs.getAnswerStatus();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class InsNotice {
        private String csTitle;
        private String csContents;

        private CustomerServiceKind csKind;
        @NotNull(message = "csType은 Null일 수 없습니다.")
        private String csType;
        @NotNull(message = "account은 Null일 수 없습니다.")
        private Account account;
        private ActiveStatus activeStatus;

        @Builder
        public InsNotice(String csTitle, String csContents, CustomerServiceKind csKind, String csType, Account account, ActiveStatus activeStatus) {
            this.csTitle = csTitle;
            this.csContents = csContents;
            this.csKind = csKind;
            this.csType = csType;
            this.account = account;
            this.activeStatus = activeStatus;
        }

        public CustomerService toEntity(){
            return CustomerService.builder()
                    .csTitle(this.csTitle)
                    .csContents(this.csContents)
                    .csKind(this.csKind)
                    .csType(this.csType)
                    .account(this.account)
                    .activeStatus(this.activeStatus)
                    .build();
        }
    }

    @Getter
    public static class InsNoticeRes {
        private String csTitle;
        private String csContents;
        private CustomerServiceKind csKind;
        private String csType;
        private String accountId;
        private ActiveStatus activeStatus;

        public InsNoticeRes(CustomerService cs) {
            this.csTitle = cs.getCsTitle();
            this.csContents = cs.getCsContents();
            this.csKind = cs.getCsKind();
            this.csType = cs.getCsType();
            this.accountId = cs.getAccount().getId();
            this.activeStatus = cs.getActiveStatus();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class UpdNotice {
        private String csTitle;
        private String csContents;

        public UpdNotice(String csTitle, String csContents) {
            this.csTitle = csTitle;
            this.csContents = csContents;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class UpdAnswerStatus {
        private AnswerStatus answerStatus;

        @Builder
        public UpdAnswerStatus(AnswerStatus answerStatus) {
            this.answerStatus = answerStatus;
        }
    }
}
