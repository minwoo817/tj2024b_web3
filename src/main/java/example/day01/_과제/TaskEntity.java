package example.day01._과제;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "task1")
@Data
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column private String name;
    @Column private String writer;
    @Column private String publisher;
    @Column private LocalDate year;
}
