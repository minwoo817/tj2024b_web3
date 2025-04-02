package example.day01._엔터티;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "task1todo")
public class Task1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable = false)
    private boolean state;

    @Column(nullable = false)
    private LocalDate createat;

    @Column(nullable = false)
    private LocalDateTime updateat;
}
