package example.day04과제.controller;

import example.day04과제.model.dto.FixDto;
import example.day04과제.service.FixService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/day04/task")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FixController {
    private final FixService fixService;

    @PostMapping("")
    public FixDto fixSave(@RequestBody FixDto fixDto){
        return fixService.fixSave(fixDto);
    }
    @GetMapping("")
    public List<FixDto> fixFindAll(){
        return fixService.fixFindAll();
    }
    @GetMapping("/view")
    public FixDto fixFindById(@RequestParam int id){
        return fixService.fixFindById(id);
    }
    @PutMapping("")
    public FixDto fixUpdate(@RequestBody FixDto fixDto){
        return fixService.fixUpdate(fixDto);
    }
    @DeleteMapping("")
    public boolean fixDelete(@RequestParam int id){
        return fixService.fixDelete(id);
    }
    @GetMapping("/page")
    public List<FixDto> fixFindByPage(
        @RequestParam(defaultValue = "1")int page,
        @RequestParam(defaultValue = "3") int size){
        return fixService.fixFindByPage(page, size);
    }
}
