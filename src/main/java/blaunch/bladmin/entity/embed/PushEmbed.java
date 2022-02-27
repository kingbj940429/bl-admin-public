package blaunch.bladmin.entity.embed;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PushEmbed {
    @Column(name = "chat_push_yn")
    private String chatPushYn;

    @Column(name = "keyword_push_yn")
    private String keywordPushYn;

    @Column(name = "active_push_yn")
    private String activePushYn;

    @Column(name = "marketing_push_yn")
    private String marketingPushYn;

    @Column(name = "push_yn")
    private String pushYn;

    @Column(name = "push_mode")
    private String pushMode;

    @Column(name = "push_sound")
    private String pushSound;
}
