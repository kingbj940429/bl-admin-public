package blaunch.bladmin.dto;

import blaunch.bladmin.entity.Account;
import blaunch.bladmin.entity.guide.Guide;
import blaunch.bladmin.entity.status.ActiveStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class GuideDto {

    @Getter
    public static class FindGuidesRes{
        private String id;
        private String type;
        private String title;
        private String nickname;
        private LocalDateTime createdDate;
        private ActiveStatus activeStatus;

        @QueryProjection
        public FindGuidesRes(Guide guide) {
            this.id = guide.getId();
            this.type = guide.getType();
            this.title = guide.getTitle();
            this.nickname = guide.getAccount().getNickname();
            this.createdDate = guide.getCreatedDate();
            this.activeStatus = guide.getActiveStatus();
        }
    }

    @Getter
    public static class FindGuideByIdRes {
        private String id;
        private String type;
        private String title;
        private String nickname;
        private String userName;
        private String userEmail;
        private LocalDateTime createdDate;
        private ActiveStatus activeStatus;
        private Long viewsCnt;
        private String subTitle;
        private String contents;
        private String imgLink;
        private String metaKeyword;
        private String metaTitle;
        private String metaDesc;
        private String ogTitle;
        private String ogDesc;
        private String ogImg;

        @QueryProjection
        public FindGuideByIdRes(Guide guide, Long viewsCnt) {
            this.id = guide.getId();
            this.type = guide.getType();
            this.title = guide.getTitle();
            this.nickname = guide.getAccount().getNickname();
            this.userName = guide.getAccount().getUserName();
            this.userEmail = guide.getAccount().getUserEmail();
            this.createdDate = guide.getCreatedDate();
            this.activeStatus = guide.getActiveStatus();
            this.viewsCnt = viewsCnt;
            this.subTitle = guide.getSubTitle();
            this.contents = guide.getContents();
            this.imgLink = guide.getImgLink();
            this.metaKeyword = guide.getMetaKeyword();
            this.metaTitle = guide.getMetaTitle();
            this.metaDesc = guide.getMetaDesc();
            this.ogTitle = guide.getOgTitle();
            this.ogDesc = guide.getOgDesc();
            this.ogImg = guide.getOgImg();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class InsGuide {
        private String type;
        private String title;
        private String subTitle;
        private String contents;
        private String metaKeyword;
        private String metaTitle;
        private String metaDesc;
        private String ogTitle;
        private String ogDesc;
        private String ogImg;
        private String imgLink;
        private Account account;
        private ActiveStatus activeStatus;

        @Builder
        public InsGuide(String type, String title, String subTitle, String contents, String metaKeyword, String metaTitle, String metaDesc, String ogTitle, String ogDesc, String ogImg, String imgLink, Account account, ActiveStatus activeStatus) {
            this.type = type;
            this.title = title;
            this.subTitle = subTitle;
            this.contents = contents;
            this.metaKeyword = metaKeyword;
            this.metaTitle = metaTitle;
            this.metaDesc = metaDesc;
            this.ogTitle = ogTitle;
            this.ogDesc = ogDesc;
            this.ogImg = ogImg;
            this.imgLink = imgLink;
            this.account = account;
            this.activeStatus = activeStatus;
        }

        public Guide toEntity(){
            return Guide.builder()
                    .type(this.type)
                    .title(this.title)
                    .subTitle(this.subTitle)
                    .contents(this.contents)
                    .metaKeyword(this.metaKeyword)
                    .metaTitle(this.metaTitle)
                    .metaDesc(this.metaDesc)
                    .ogTitle(this.ogTitle)
                    .ogDesc(this.ogDesc)
                    .ogImg(this.ogImg)
                    .imgLink(this.imgLink)
                    .account(this.account)
                    .activeStatus(this.activeStatus)
                    .build();
        }
    }

    @Getter
    public static class InsGuideRes {
        private String type;
        private String title;
        private String subTitle;
        private String contents;
        private String metaKeyword;
        private String metaTitle;
        private String metaDesc;
        private String ogTitle;
        private String ogDesc;
        private String ogImg;
        private String imgLink;
        private String accountId;
        private ActiveStatus activeStatus;

        public InsGuideRes(Guide guide) {
            this.type = guide.getType();
            this.title = guide.getTitle();
            this.subTitle = guide.getSubTitle();
            this.contents = guide.getContents();
            this.metaKeyword = guide.getMetaKeyword();
            this.metaTitle = guide.getMetaTitle();
            this.metaDesc = guide.getMetaDesc();
            this.ogTitle = guide.getOgTitle();
            this.ogDesc = guide.getOgDesc();
            this.ogImg = guide.getOgImg();
            this.imgLink = guide.getImgLink();
            this.accountId = guide.getAccount().getId();
            this.activeStatus = guide.getActiveStatus();
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
    public static class UpdGuide {
        private String type;
        private String title;
        private String subTitle;
        private String contents;
        private String metaKeyword;
        private String metaTitle;
        private String metaDesc;
        private String ogTitle;
        private String ogDesc;
        private String ogImg;
        private String imgLink;
        private ActiveStatus activeStatus;

        @Builder
        public UpdGuide(String type, String title, String subTitle, String contents, String metaKeyword, String metaTitle, String metaDesc, String ogTitle, String ogDesc, String ogImg, String imgLink, ActiveStatus activeStatus) {
            this.type = type;
            this.title = title;
            this.subTitle = subTitle;
            this.contents = contents;
            this.metaKeyword = metaKeyword;
            this.metaTitle = metaTitle;
            this.metaDesc = metaDesc;
            this.ogTitle = ogTitle;
            this.ogDesc = ogDesc;
            this.ogImg = ogImg;
            this.imgLink = imgLink;
            this.activeStatus = activeStatus;
        }
    }

    @Getter
    public static class DeleteRes {
        private String id;

        @Builder
        public DeleteRes(Guide guide) {
            this.id = guide.getId();
        }
    }
}
