package blaunch.bladmin.entity.guide;

import blaunch.bladmin.entity.Account;
import blaunch.bladmin.entity.base.BaseTimeEntity;
import blaunch.bladmin.entity.status.ActiveStatus;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "bl_guide")
public class Guide extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "guide_id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    private String type;
    private String title;

    @Column(name = "sub_title")
    private String subTitle;
    private String contents;

    @Column(name = "meta_keyword")
    private String metaKeyword;

    @Column(name = "meta_title")
    private String metaTitle;

    @Column(name = "meta_desc")
    private String metaDesc;

    @Column(name = "og_title")
    private String ogTitle;

    @Column(name = "og_desc")
    private String ogDesc;

    @Column(name = "og_img")
    private String ogImg;

    @Column(name = "img_link")
    private String imgLink;

    @Enumerated(EnumType.STRING)
    @Column(name = "active_status")
    private ActiveStatus activeStatus;

    //==연관관계 메서드==//

    //==생성, 수정 메서드==//
    public void updActiveStatus(ActiveStatus activeStatus){
        this.activeStatus = activeStatus;
    }

    public void updGuide(String type, String title, String subTitle, String contents,
                         String metaKeyword, String metaTitle, String metaDesc,
                         String ogTitle, String ogDesc, String ogImg,
                         String imgLink, ActiveStatus activeStatus){

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

    //==비즈니스 로직==//

    //==조회 로직==//

}
