package blaunch.bladmin.entity;

import blaunch.bladmin.entity.base.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"account", "authority"})
@Table(name = "bl_account_authority")
public class AccountAuthority extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_authority_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authority_id")
    private Authority authority;

    //==연관관계 메서드==//

    //==생성,수정 메서드==//
    public void updateAuthority(Authority authority){
        this.authority = authority;
    }
    //==비즈니스 로직==//

    //==조회 로직==//
}
