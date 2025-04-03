package example.day03.과제3;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class StudentDto {
    private int sno;
    private String sname;
    // + 학생 등록시 특정한 과정의 pk번호
    private int cno;
    // + toEntity
    public StudentEntity toEntity(){
        return StudentEntity.builder()
                .sno( this.sno )
                .sname( this.sname )
                .build();
    }
}