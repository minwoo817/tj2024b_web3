package web.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import web.model.entity.ProductEntity;

import java.util.List;

@Repository
public interface ProductEntityRepository extends JpaRepository<ProductEntity, Long> {
    // 1. JPA 기본적인 함수 제공
    // save , findAll, findById, delete 등

    // 2. 쿼리 메소드 규칙 : 명명규칙(카멜)
        // findBy : select ~
        // findByCno[x] : ProductEntity 에는 cno 가 존재하지 않는다
        // findByPname[o] : ProductEntity 에는 pname 이 존재한다.
    List<ProductEntity> findByCategoryEntityCno(long cno);
    // 3. 네이티브 쿼리 규칙 : mysql 코드
    @Query(value = "select * from product where cno = :cno", nativeQuery = true)
    List<ProductEntity> nativeQuery(long cno);

     // (*) 카테고리별 제품키워드 검색
     // @Query(value = "SQL 작성", nativeQuery = true)
    // SQL 문에서 매개변수 사용시 매개변수 앞에 :(콜론) vs 마이바티스 #{} DAO ?
    @Query(value = "select * from product"+
            "where(:cno is null or :cno = 0 or cno = :cno)"+
            "and (:keyword is null or pname like %:keyword%)", nativeQuery = true)
    Page<ProductEntity> findBySearch(Long cno, String keyword , Pageable pageable);
    // 4. * JPQL 규칠 : 자바 만든 sql 코드/메소드
}
