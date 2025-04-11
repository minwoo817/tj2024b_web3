package example.evaluation.controller;

import example.evaluation.model.dto.ReviewDto;
import example.evaluation.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("")
    public ReviewDto reviewSave(@RequestBody ReviewDto reviewDto){
        return reviewService.reviewSave(reviewDto);
    }
    @GetMapping("")
    public List<ReviewDto> reviewFindAll(@RequestParam int id){
        return reviewService.reviewFindAll(id);
    }

    @DeleteMapping("")
    public boolean reviewDelete(@RequestParam int rid, String rpw){
        return  reviewService.reviewDelete(rid, rpw);
    }

}
