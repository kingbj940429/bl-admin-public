package blaunch.bladmin.exception;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorDto {

    private String message;
    private String code;
    private int status;
    private List<FieldError> errors = new ArrayList<>();

    private List<FieldError> initErrors(List<FieldError> errors) {
        return (errors == null) ? new ArrayList<>() : errors;
    }

    @Builder
    public ErrorDto(String message, String code, int status, List<FieldError> errors) {
        this.message = message;
        this.code = code;
        this.status = status;
        this.errors = initErrors(errors);
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FieldError {
        private String field;
        private String value;
        private String reason;

        @Builder
        public FieldError(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }
    }

}