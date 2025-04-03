package example.day03._JPA연관관계;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data@Builder // 롬복
@Entity // 해당 클래스는 데이터베이스 와 영속관계로 사용
@Table(name = "day03category") // DB테이블명 정의
public class Category {
    @Id // primary key
    @GeneratedValue( strategy = GenerationType.IDENTITY ) // auto_increment
    private int cno; // 카테고리번호
    private String cname; // 카테고리명

    // + 양방향 , 게시물 여러개 참조
    // @OneToMany( mappedBy = "단방향의멤버변수명" ) // 양방향 참조테이블은 자바에서만 참조한다.
    @ToString.Exclude // 순환참조 방지
    @Builder.Default // 빌더패턴 사용시 초기값 대입
    @OneToMany( mappedBy = "category" , cascade = CascadeType.ALL , fetch = FetchType.LAZY) // 제약조건옵션 : 만약에 PK가 삭제되면 FK????
    private List<Board> boardList = new ArrayList<>();
}

    /*
        - 영속성 제약조건 옵션 (1. cascade, 2. fetch)
        [1] @OneToMany(cascade = CascadeType.XXX)
        1. cascade = CascadeType.ALL        : 부모(PK)가 삭제/수정/저장 되면 자식(FK)도 같이 삭제/수정/저장, REMOVE, MERGE, PERSIST
        2. cascade = CascadeType.REMOVE     : 부모(PK)가 삭제되면 자식(FK)도 같이 삭제됨. REMOVE
        3. cascade = CascadeType.MERGE      : 부모(PK)가 수정되면 자식(FK)도 같이 수정됨. MERGE
        4. cascade = CascadeType.DETACH     : 부모(PK)가 영속성 해제하면 자식(FK)도 같이 해제됨. DETACH
        5. cascade = CascadeType.PERSIST    : 부모(PK)가 저장되면 자식(FK)도 같이 저장됨 PERSIST
        6. cascade = CascadeType.REFRESH    : 부모(PK)가 새로 불러올때 자식(FK) 같이 새로 불러온다. (새로고침)

        [2] @OneToMany(fetch = FetchType.XXX)
        1. fetch = FetchType.EAGER      : (기본값) : 해당 엔티티를 조회할때 참조되는 모든 객체를 즉시 불러온다.
            단점 : 불필요한 엔티티가 모두 가져오기 때문에 (메모리)로드 기능 저하
        2. fetch = FetchType.LAZY       : 지연 로딩 : 해당 엔티티를 조회(.findXX()) 할때 참조되는 객체 를 불러오지않고, .getXXX() 등 참조할때 참조되는 객체를 불러온다
            특징 : 초기 로딩 빠르다. 메모리 사용량을 적절하게 사용하므로 성능 최적화

    */