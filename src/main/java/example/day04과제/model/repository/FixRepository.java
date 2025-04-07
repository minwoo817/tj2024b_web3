package example.day04과제.model.repository;

import example.day04과제.model.entity.FixEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FixRepository extends JpaRepository<FixEntity, Integer> {
}
