package example.evaluation.model.entity;
import example.day04.model.dto.TodoDto;
import example.evaluation.model.dto.BookDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // 데이터베이스의 테이블과 영속 관계
@Table( name = "book") // 데이터베이스의 테이블명 정의
@Data@NoArgsConstructor@AllArgsConstructor@Builder // 룸북
public class BookEntity extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String writer;
    private String context;
    private String pw;

    public BookDto bookDto(){
        return BookDto.builder()
                .id(this.id)
                .title(this.title)
                .writer(this.writer)
                .context(this.context)
                .pw(this.pw)
                .createAt(this.getCreateAt())
                .build();
    }
}
