package example.evaluation.model.dto;

import example.evaluation.model.entity.ReviewEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {
    private int rid;
    private String reviews;
    private String rpw;
    private int id;

    public ReviewEntity reviewEntity(){
        return ReviewEntity.builder()
                .rid(this.rid)
                .reviews(this.reviews)
                .rpw(this.rpw)
                .id(this.id)
                .build();
    }
}
