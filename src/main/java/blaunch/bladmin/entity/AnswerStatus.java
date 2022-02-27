package blaunch.bladmin.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AnswerStatus {
    YES("Yes", "답변 완료"),
    NO("No", "답변 대기");

    private String code;
    private String name;
}
