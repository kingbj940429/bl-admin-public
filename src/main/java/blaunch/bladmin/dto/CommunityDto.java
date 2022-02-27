package blaunch.bladmin.dto;

import blaunch.bladmin.entity.Account;
import blaunch.bladmin.entity.Community.Community;
import blaunch.bladmin.entity.status.ActiveStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CommunityDto {

    @Getter
    public static class FindCommunitiesRes {
        private String id;
        private String category;
        private String title;
        private String nickname;
        private ActiveStatus activeStatus;
        private LocalDateTime createdDate;

        public FindCommunitiesRes(Community community) {
            this.id = community.getId();
            this.category = community.getCategory();
            this.title = community.getTitle();
            this.nickname = community.getAccount().getNickname();
            this.activeStatus = community.getActiveStatus();
            this.createdDate = community.getCreatedDate();
        }
    }

    @Getter
    public static class FindCommunityRes {
        private String id;
        private String category;
        private LocalDateTime createdDate;
        private Long viewsCnt;
        private Long favoriteCnt;
        private Long commentCnt;
        private String nickname;
        private String userName;
        private String userId;
        private ActiveStatus activeStatus;

        private String title;
        private String contents;
        private String imgLinkAdd;
        private String tag;

        @QueryProjection
        public FindCommunityRes(Community community, Long viewsCnt, Long favoriteCnt, Long commentCnt) {
            this.id = community.getId();
            this.category = community.getCategory();
            this.createdDate = community.getCreatedDate();
            this.viewsCnt = viewsCnt;
            this.favoriteCnt = favoriteCnt;
            this.commentCnt = commentCnt;
            this.nickname = community.getAccount().getNickname();
            this.userName = community.getAccount().getUserName();
            this.userId = community.getAccount().getUserId();
            this.activeStatus = community.getActiveStatus();
            this.title = community.getTitle();
            this.contents = community.getContents();
            this.imgLinkAdd = community.getImgLinkAdd();
            this.tag = community.getTag();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class UpdActiveStatus {
        @NotNull(message = "activeStatus은 Null 일 수 없습니다.")
        private ActiveStatus activeStatus;

        @Builder
        public UpdActiveStatus(ActiveStatus activeStatus) {
            this.activeStatus = activeStatus;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class InsCommunity {
        @NotNull(message = "title는 Null 일 수 없습니다.")
        private String title;
        private String contents;

        @NotNull(message = "category는 Null 일 수 없습니다.")
        private String category;
        private String tag;

        @NotNull(message = "activeStatus는 Null 일 수 없습니다.")
        private ActiveStatus activeStatus;

        private Account account;

        @Builder
        public InsCommunity(String title, String contents, String category, String tag, ActiveStatus activeStatus, Account account) {
            this.title = title;
            this.contents = contents;
            this.category = category;
            this.tag = tag;
            this.activeStatus = activeStatus;
            this.account = account;
        }

        public Community toEntity(){
            return Community.builder()
                    .title(this.title)
                    .contents(this.contents)
                    .category(this.category)
                    .tag(this.tag)
                    .activeStatus(this.activeStatus)
                    .account(this.account)
                    .build();
        }
    }

    @Getter
    public static class InsCommunityRes {
        private String title;
        private String contents;
        private String category;
        private String tag;
        private ActiveStatus activeStatus;
        private String accountId;

        public InsCommunityRes(Community community) {
            this.title = community.getTitle();
            this.contents = community.getContents();
            this.category = community.getCategory();
            this.tag = community.getTag();
            this.activeStatus = community.getActiveStatus();
            this.accountId = community.getAccount().getId();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class UpdCommunity {
        @NotNull(message = "title는 Null 일 수 없습니다.")
        private String title;
        private String contents;

        @NotNull(message = "category는 Null 일 수 없습니다.")
        private String category;
        private String tag;

        @Builder
        public UpdCommunity(String title, String contents, String category, String tag) {
            this.title = title;
            this.contents = contents;
            this.category = category;
            this.tag = tag;
        }
    }

    @Getter
    public static class DeleteRes {
        private String id;

        @Builder
        public DeleteRes(Community community) {
            this.id = community.getId();
        }
    }
}
