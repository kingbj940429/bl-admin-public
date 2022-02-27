package blaunch.bladmin.entity;

import blaunch.bladmin.entity.base.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "bl_token")
public class Token extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", unique = true)
    private Account account;

    private String name;

    @Builder
    public Token(Long id, Account account, String name) {
        this.id = id;
        this.account = account;
        this.name = name;
    }

    public void updateToken(Token token) {
        this.name = token.getName();
    }
}
