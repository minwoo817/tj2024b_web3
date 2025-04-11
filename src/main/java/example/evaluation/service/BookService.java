package example.evaluation.service;

import example.evaluation.model.dto.BookDto;
import example.evaluation.model.entity.BookEntity;
import example.evaluation.model.repository.BookEntityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {
    private  final BookEntityRepository bookEntityRepository;

    public BookDto bookSave(BookDto bookDto){
        BookEntity bookEntity = bookDto.bookEntity();
        BookEntity saveEntity = bookEntityRepository.save(bookEntity);
        if(bookEntity.getId()>0){
            return bookEntity.bookDto();
        }else {
            return null;
        }
    }

    public List<BookDto> bookFindAll(){
        List<BookEntity> bookEntityList = bookEntityRepository.findAll();
        List<BookDto> bookDtoList = new ArrayList<>();
        for(int i = 0; i < bookEntityList.size(); i++){
            BookDto bookDto = bookEntityList.get(i).bookDto();
            bookDtoList.add(bookDto);
        }
        return bookDtoList;
    }

    public BookDto bookFindById(int id){
        Optional<BookEntity> optional = bookEntityRepository.findById(id);
        if(optional.isPresent()){
            BookEntity bookEntity = optional.get();
            BookDto bookDto = bookEntity.bookDto();
            return bookDto;
        }
        return null;
    }

    public BookDto bookUpdate(BookDto bookDto) {
        Optional<BookEntity> optional = bookEntityRepository.findById(bookDto.getId());
        if (optional.isPresent()) {
            BookEntity bookEntity = optional.get();

            System.out.println("🟡 서버 DB PW: [" + bookEntity.getPw() + "]");
            System.out.println("🟡 클라이언트 PW: [" + bookDto.getPw() + "]");
            if (bookEntity.getPw().equals(bookDto.getPw())) {
                bookEntity.setTitle(bookDto.getTitle());
                bookEntity.setWriter(bookDto.getWriter());
                bookEntity.setContext(bookDto.getContext());
                return bookEntity.bookDto();
            } else {
                return null;
            }
        }
        return null;
    }


    public boolean bookDelete(int id, String pw) {
        Optional<BookEntity> optional = bookEntityRepository.findById(id);
        if (optional.isPresent()) {
            BookEntity bookEntity = optional.get();
            if (bookEntity.getPw().equals(pw)) {
                bookEntityRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}
