package example.day01._엔터티;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity // 해당 클래스의 DB 테이블과 매핑 관계 주입 (영속성 컨텍스트 저장)
@Table(name = "exam2") // DB 테이블명 정의, 생략시 클래스명으로 정의한다.
public class ExamEntity2 {
    @Id // primary key 제약조건 정의
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private long id;

    @Column(nullable = true , unique = false)
    private String col1;

    @Column(nullable = false , unique = true)
    private String col2;

    @Column(columnDefinition = "longtext")
    private String col3;

    @Column(name = "제품명", length = 30, insertable = true , updatable = true)
    private String col4;

    @Column private LocalDate col5; // date
    @Column private LocalDateTime col6; // datetime


}
