package example.evaluation.model.entity;

import example.evaluation.model.dto.ReviewDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "review")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewEntity extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rid;
    private String reviews;
    private String rpw;
    private int id;

    public ReviewDto reviewDto(){
        return ReviewDto.builder()
                .rid(this.rid)
                .reviews(this.reviews)
                .rpw(this.rpw)
                .id(this.id)
                .build();
    }
}
