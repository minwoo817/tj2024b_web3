package example.evaluation.controller;

import example.evaluation.model.dto.BookDto;
import example.evaluation.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BookController {
    private final BookService bookService;

    @PostMapping("")
    public BookDto bookSave(@RequestBody BookDto bookDto){
        return bookService.bookSave(bookDto);
    }

    @GetMapping("")
    public List<BookDto> bookFindAll(){
        return bookService.bookFindAll();
    }

    @GetMapping("/view")
    public BookDto bookFindById(@RequestParam int id){
        return bookService.bookFindById(id);
    }

    @PutMapping("")
    public BookDto bookUpdate(@RequestBody BookDto bookDto){
        return bookService.bookUpdate(bookDto);
    }

    @DeleteMapping("")
    public boolean bookDelete(@RequestParam int id, String pw){
        return bookService.bookDelete(id, pw);
    }
}

