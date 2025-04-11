package example.evaluation.model.dto;
import example.day04.model.entity.TodoEntity;
import example.evaluation.model.entity.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data@NoArgsConstructor@AllArgsConstructor@Builder
public class BookDto {
    private int id;
    private String title;
    private String writer;
    private String context;
    private String pw;
    private LocalDateTime createAt;

    public BookEntity bookEntity(){
        return BookEntity.builder()
                .id(this.id)
                .title(this.title)
                .writer(this.writer)
                .context(this.context)
                .pw(this.pw)
                .build();
    }
}
