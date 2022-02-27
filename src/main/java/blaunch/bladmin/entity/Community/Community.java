package blaunch.bladmin.entity.Community;

import blaunch.bladmin.entity.Account;
import blaunch.bladmin.entity.base.BaseTimeEntity;
import blaunch.bladmin.entity.status.ActiveStatus;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@ToString
@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "bl_board")
public class Community extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "board_id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    private String title;
    private String contents;
    private String category;//사업정보: 0001 품앗이:0002 속닥속닥:0003
    private String tag;

    @Column(name = "img_link")
    private String imgLink;

    @Column(name = "img_link_add")
    private String imgLinkAdd;

    @Column(name = "active_status")
    @Enumerated(EnumType.STRING)
    private ActiveStatus activeStatus;

    //==연관관계 메서드==//

    //==생성, 수정 메서드==//
    public void updActiveStatus(ActiveStatus activeStatus){
        this.activeStatus = activeStatus;
    }

    public void updCommunity(String category, String title, String contents, String tag){
        this.category = category;
        this.title = title;
        this.contents = contents;
        this.tag = tag;
    }

    //==비즈니스 로직==//

    //==조회 로직==//
}
