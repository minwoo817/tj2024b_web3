package example.day03._자바참조관계;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
public class Category {
    // 클래스 : 인스턴스(객체)의 설계/타입
    private int 카테고리번호; // 기본타입(int) 멤버변수 선언
    private String 카테고리명; // 참조타입(String) 멤버변수 선언
    // private Board board; // 양방향 참조 , 1:1 참조
    @ToString.Exclude // toString 메소드에 제외
    private List<Board> boardList; // 양방향 참조 1:N 참조 , 하나의 카테고리가 여러개 게시물 참조한다
}
