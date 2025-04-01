package example._리포지토리;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 엔티티(테이블) 조작(DML : insert/update/delete/select) 하는 인터페이스
// 해당 인터페이스에 JpaRepository< 조작할엔티티클래스명 , 해당엔티티의ID타입 > 상속
// < > : 제네릭
@Repository // 스프링 컨테이너에 빈 등록
public interface ExamEntityRepository
        extends JpaRepository< ExamEntity , String > {
}

// CRUD 메소드
// 1. .save(저장할엔티티객체);
//      : 존재하지 않은 PK이면 insert, 존재하는 P이면 update
//      반환값 : insert/update 이후 영속(연결/매핑)된 객체(엔티티)

// 2. .findAll();
//      : 모든 엔티티를 select 한다.
//      반환값 : 리스트타입을 반환된다.

// 3. .findById();
//      : pk값과 일치하는 엔티티를 select 한다.
//      반환값 : Optional<엔티티>

// Optional 클래스 : null 관련된 메소드 제공하는 클래스
// -> nullPointerException 방지하고자 객체를 포장하는 클래스
