package blaunch.bladmin.entity;

import blaunch.bladmin.entity.base.BaseTimeEntity;
import blaunch.bladmin.entity.embed.PushEmbed;
import blaunch.bladmin.entity.status.PlatformStatus;
import blaunch.bladmin.entity.status.UserStatus;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "bl_account")
public class Account extends BaseTimeEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "account_id")
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "platform")
    private PlatformStatus platformStatus;

    @Column(name = "user_id")
    private String userId;

    @Email
    private String userEmail;
    private String nickname;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_phone")
    private String userPhone;

    private String address;
    private String gender;
    private String birthday;
    private String introduce;
    private String job;

    @Column(name = "active_field")
    private String activeField;

    @Column(name = "img_link")
    private String imgLink;

    @Column(name = "term1_yn")
    private String term1Yn;
    @Column(name = "term2_yn")
    private String term2Yn;
    @Column(name = "term3_yn")
    private String term3Yn;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status")
    private UserStatus userStatus;

    @Column(name = "last_login_date")
    private LocalDateTime LastLoginDate;

    @Embedded
    private PushEmbed pushEmbed;

    @Column(name = "device_token")
    private String deviceToken;

    @Transient
    @Builder.Default
    private List<AccountAuthority> accountAuthorities = new ArrayList<>();

    @Transient
    private Token token;

    @Transient
    private int boardCount;

    //==연관관계 메서드==//

    //==생성, 수정 메서드==//
    public void updUserStatus(UserStatus userStatus){
        this.userStatus = userStatus;
    }

    //==비즈니스 로직==//

    //==조회 로직==//
    public PushEmbed getPushEmbed() {
        return pushEmbed == null ? new PushEmbed() : pushEmbed;
    }
}
