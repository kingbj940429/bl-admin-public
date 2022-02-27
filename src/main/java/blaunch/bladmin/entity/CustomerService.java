package blaunch.bladmin.entity;

import blaunch.bladmin.entity.base.BaseEntity;
import blaunch.bladmin.entity.base.BaseTimeEntity;
import blaunch.bladmin.entity.status.ActiveStatus;
import blaunch.bladmin.entity.status.CustomerServiceKind;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "bl_cs")
public class CustomerService extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "cs_id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(name = "cs_kind")
    private CustomerServiceKind csKind;

    @Column(name = "cs_type")
    private String csType;

    @Column(name = "cs_title")
    private String csTitle;

    @Column(name = "cs_content")
    private String csContents;

    @Enumerated(EnumType.STRING)
    @Column(name = "active_status")
    private ActiveStatus activeStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "answer_status")
    private AnswerStatus answerStatus;

    @Transient
    private CsAnswer csAnswer;

    //==연관관계 메서드==//

    //==생성, 수정 메서드==//
    public void updNotice(String csTitle, String csContents){
        this.csTitle = csTitle;
        this.csContents = csContents;
    }

    public void updAnswerStatus(AnswerStatus answerStatus){
        this.answerStatus = answerStatus;
    }

    //==비즈니스 로직==//

    //==조회 로직==//
}
