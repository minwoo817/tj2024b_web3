package example.day04.model.entity;

import example.day04.model.dto.TodoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // 데이터베이스의 테이블과 영속 관계
@Table(name = "todo") // 데이터베이스의 테이블명 정의
@Data // 롬복
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoEntity extends BaseTime{
    @Id // pk 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int id; //식별번호
    private String title; // 제목
    private String content; // 내용
    private boolean done; // 상태

    // entity -> dto 변환 함수
    public TodoDto toDto(){
        // 변환할 필드 선택하여 dto 객체 만들기
        return TodoDto.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .done(this.done)
                .createDateTime(this.getCreateAt()) // BaseTime 에 존재함
                .build();
    }



}
