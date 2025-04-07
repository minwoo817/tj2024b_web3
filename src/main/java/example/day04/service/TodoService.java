package example.day04.service;

import example.day04.model.dto.TodoDto;
import example.day04.model.entity.TodoEntity;
import example.day04.model.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    // 1. 등록
    public TodoDto todoSave( TodoDto todoDto ){
        // 1. dto 를 entity 변환하기
        TodoEntity todoEntity = todoDto.toEntity();
        // 2. entity를 save(영속화/db레코드 매칭/등록) 한다.
        TodoEntity saveEntity = todoRepository.save( todoEntity );
        // 3. save 로 부터 반환된 엔티티(영속화)된 결과가 존재하면
        if( saveEntity.getId() > 0 ){
            return saveEntity.toDto(); // entity를 dto로 변환하여 반환
        }else{ // 결과가 존재하지 않으면
            return null; // null 반환
        }
    } // f end
    // 2. 전체조회
    public List<TodoDto> todoFindAll( ){
        // 1. 모든 entity 조회 , findAll()
        List<TodoEntity> todoEntityList = todoRepository.findAll();
        // 2. 모든 entity 리스트 를 dto 리스트 변환하다.
        List<TodoDto> todoDtoList = new ArrayList<>(); // 2-1 : dto 리스트 생성한다.
        for( int index = 0 ; index < todoEntityList.size() ; index++ ){ // 2-2 : entity 리스트를 순회
            TodoDto todoDto = todoEntityList.get( index ).toDto(); // 2-3 : index번째 entity 를 dto로 변환
            todoDtoList.add( todoDto ); // 2-4 : dto 리스트에 저장
        } // for end
        // 3. 결과 반환
        return todoDtoList;
        // 방법 2 stream
        // return todoRepository.findAll().stream().map(TodoEntity::toDto).collect(Collectors.toList());
    } // f end

    // 3. 개별 조회
    public TodoDto todoFindById(int id){
        // 1. pk(식별번호) 이용한 entity 조회하기, findById()
        // Optional 클래스 : null 제어하는 메소드들을 제공하는 클래스
        Optional<TodoEntity> optional = todoRepository.findById(id);
        // 2. 조회한 결과가 존재하면 , .isPresent()
        if(optional.isPresent()){
            // 3. Optional 에서 entity 꺼내기
            TodoEntity todoEntity = optional.get();
            // 4. dto로 변환
            TodoDto todoDto = todoEntity.toDto();
            // 5. 반환
            return todoDto;
        }
        return null;

        // 방법2 stream
//        return todoRepository.findById(id)
//                // Optional 의 데이터가 null 이 아니면 map 실행하여 dto 변환후 반환
//                .map(TodoEntity::toDto)
//                // Optional 의 데이터가 null 이면 null 반환
//                .orElse(null);

    }

    // 4. 개별 수정 + @Transactional
    public TodoDto todoUpdate(TodoDto todoDto){
        // 1. 수정할 id 로 엔티티 조회한다.
        Optional<TodoEntity> optional = todoRepository.findById(todoDto.getId());
        // 2. 존재하면 수정하고 존재하지 않으면 null 반환, .isPresent()
        if(optional.isPresent()){
            // 3. 엔티티 꺼내기
            TodoEntity todoEntity = optional.get();
            // 4. 엔티티 수정하기, 입력받은 dto 값을 엔티티에 대입하기
            todoEntity.setTitle((todoDto.getTitle()));
            todoEntity.setContent(todoDto.getContent());
            todoEntity.setDone(todoDto.isDone()); // boolean 의 getter는 isXXX() 이다.
            return todoEntity.toDto();
        }
        return null;
    }

    public boolean todoDelete(int id){
        // 1. id를 이용하여 엔티티(존재여부) 조회하기
            // findById 반호낱탕비 : Optional / existById(존재하는지) 반환타입 : boolean
        boolean result = todoRepository.existsById(id);
        // 2. 만약 존재하면
        if(result == true){
            // 3. 영속성 제거/ .deleteById(pk)
            todoRepository.deleteById(id);
            return true;
        }
        return false;

        // 방법 2. stream
//        return todoRepository.findById(id)
//                .map((entity) -> {
//                    todoRepository.deleteById(id);
//                    return true;
//                })
//                .orElse(false);
    }

    // 6. 전체조회(페이징처리)
    public List<TodoDto> todoFindByPage(int page, int size){
        // page : 현재 조회중인 페이지 번호
        // size : 페이지 1개 당 조회할 자료 개수
        // 1. PageRequest 클래스
            // PageRequest.of(조회할페이지번호, 자료개수)
            // - 조회할 페이지 번호는 1페이지가 0부터 시작
            // - 페이지당 조회할 자료 개수
            // - Sort.by : 정렬
            // - Sort.by(Sort.Direction.ASC , 필드명) : 오름차순
            // - Sort.by(Sort.Direction.DESC , 필드명) : 내림차순(최신순)
        PageRequest pageRequest = PageRequest.of(page-1, size);
        // 2. pageRequest 객체를 findXX에 매개변수로 대입한다 . findAll(페이징객체); , 반환타입 : Page 타입 = List 타입 유사
        Page<TodoEntity> todoEntityPage = todoRepository.findAll(pageRequest);
        // 3. page 타입의 entity 를 dto로 변환
        List<TodoDto> todoDtoList = new ArrayList<>();
        for(int index = 0; index < todoEntityPage.getContent().size(); index++){
            TodoDto todoDto = todoEntityPage.getContent().get(index).toDto();
            todoDtoList.add(todoDto);
        }
        return todoDtoList;

//        // stream
//        return todoRepository.findAll(pageRequest).stream()
//                .map(TodoEntity::toDto)
//                .collect(Collectors.toList());
    }

} // class end

//        System.out.println("todoEntityPage = " + todoEntityPage);
//        System.out.println(todoEntityPage.getTotalPages()); // getTotalPages() : 전체 페이지 수 반환
//        System.out.println(todoEntityPage.getTotalElements()); // getTotalElements() : 전체 게시물 수 반환
//        System.out.println(todoEntityPage.getContent()); // getContent() : 조회된 자료의 Page 타입 --> List 타입으로 변환