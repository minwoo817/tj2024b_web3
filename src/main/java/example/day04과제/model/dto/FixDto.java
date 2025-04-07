package example.day04과제.model.dto;

import example.day04과제.model.entity.FixEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FixDto {
    private int id; //식별번호
    private String name; // 제목
    private String description; // 내용
    private int quantity; // 상태
    // + 등록날짜
    private LocalDateTime createDateTime;

    // dto -> entity 변환 함수
    public FixEntity fixEntity() {
        return FixEntity.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .quantity(this.quantity)
                .build();

    }

}