package blaunch.bladmin.entity;

import blaunch.bladmin.entity.base.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@ToString(exclude = {"accountAuthorities"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bl_authority")
public class Authority extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    private Long id;

    @Column(name = "name")
    private String authorityName;

    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "authority")
    @Transient
    @Builder.Default
    private List<AccountAuthority> accountAuthorities = new ArrayList<>();
}
