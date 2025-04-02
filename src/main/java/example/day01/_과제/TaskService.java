
package example.day01._과제;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskEntityRepository taskEntityRepository;

    public  boolean post(TaskEntity taskEntity){
        System.out.println("TaskService.post");
        System.out.println("taskEntity = " + taskEntity);

        TaskEntity taskEntity2 = taskEntityRepository.save(taskEntity);
        return true;
    }

    public List<TaskEntity> get(){
        List<TaskEntity> TaskList = taskEntityRepository.findAll();
        return TaskList;
    }
    @Transactional
    public boolean put(TaskEntity taskEntity){
        Optional<TaskEntity> optionalTaskEntity = taskEntityRepository.findById(taskEntity.getId());
        if(optionalTaskEntity.isPresent()){
            TaskEntity entity = optionalTaskEntity.get();
            entity.setName(taskEntity.getName());
            entity.setWriter(taskEntity.getWriter());
            entity.setPublisher(taskEntity.getPublisher());
            entity.setYear(taskEntity.getYear());
            return true;
        }
        return false;
    }

    public boolean delete(int id){
        taskEntityRepository.deleteById(id);
        return true;
    }
}
