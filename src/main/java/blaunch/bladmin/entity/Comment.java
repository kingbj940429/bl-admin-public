package blaunch.bladmin.entity;

import blaunch.bladmin.entity.base.BaseTimeEntity;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "bl_comment")
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "comment_id")
    private String id;

    @Column(name = "parent_comment_id")
    private String parentCommentId;

    @Column(name = "comment_order")
    private int commentOrder;

    @Column(name = "type_id")
    private String typeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "comment_content")
    private String contents;
}
