package example.day04과제.model.entity;

import example.day04.model.entity.BaseTime;
import example.day04과제.model.dto.FixDto;
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
public class FixEntity extends BaseTime {
    @Id // pk 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int id; //식별번호
    private String name; // 제목
    private String description; // 내용
    private int quantity; // 상태

    // entity -> dto 변환 함수
    public FixDto fixDto() {
        // 변환할 필드 선택하여 dto 객체 만들기
        return FixDto.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .quantity(this.quantity)
                .createAt(this.getCreateAt()) // BaseTime 에 존재함
                .build();
    }

}