package example.evaluation.model.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseTime {
    // 1. 생성날짜
    @CreatedDate
    private LocalDateTime createAt;

    // 2. 수정날짜
    @LastModifiedDate
    private LocalDateTime updateAt;
}
