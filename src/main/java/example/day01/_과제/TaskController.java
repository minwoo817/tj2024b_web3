package example.day01._과제;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/day01/task1")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public boolean post(@RequestBody TaskEntity taskEntity){
        boolean result = taskService.post(taskEntity);
        return result;
    }

    @GetMapping
    public List<TaskEntity> get(){
        return taskService.get();
    }

    @PutMapping
    public boolean put(@RequestBody TaskEntity taskEntity){
        boolean result = taskService.put(taskEntity);
        return result;
    }

    @DeleteMapping
    public boolean delete(@RequestParam int id){
        boolean result = taskService.delete(id);
        return result;
    }
}