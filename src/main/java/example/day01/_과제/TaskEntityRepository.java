package example.day01._과제;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskEntityRepository extends JpaRepository<TaskEntity, Integer> {
}
