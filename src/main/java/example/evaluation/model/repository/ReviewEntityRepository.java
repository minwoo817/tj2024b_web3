package example.evaluation.model.repository;

import example.evaluation.model.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewEntityRepository extends JpaRepository<ReviewEntity, Integer> {
    List<ReviewEntity> findAllById(int id);
}
