package blaunch.bladmin.dto;

import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseDto {

    @Getter
    public static class Single<T> {
        private T content;

        public Single(T content) {
            this.content = content;
        }
    }

    @Getter
    public static class ResultList<T> {
        private List<T> content = new ArrayList<>();

        private List<T> initContents(List<T> content){
            return (content == null) ? new ArrayList<>() : content;
        }

        public ResultList(List<T> content) {
            this.content = initContents(content);
        }
    }
}
