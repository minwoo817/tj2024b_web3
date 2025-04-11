package example.evaluation.service;

import example.evaluation.model.dto.ReviewDto;
import example.evaluation.model.entity.ReviewEntity;
import example.evaluation.model.repository.ReviewEntityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewEntityRepository reviewEntityRepository;

    // 1. 리뷰 작성
    public ReviewDto reviewSave(ReviewDto reviewDto) {
        ReviewEntity reviewEntity = reviewDto.reviewEntity();
        ReviewEntity saveEntity = reviewEntityRepository.save(reviewEntity);
        if (saveEntity.getRid() > 0) {
            return saveEntity.reviewDto();
        } else {
            return null;
        }
    }

    // 2. 책 별 리뷰 전체 조회
    public List<ReviewDto> reviewFindAll(int id) {
        List<ReviewEntity> reviewEntityList = reviewEntityRepository.findAllById(id);
        List<ReviewDto> reviewDtoList = new ArrayList<>();
        for (ReviewEntity entity : reviewEntityList) {
            if (entity.getId() == id) {
                reviewDtoList.add(entity.reviewDto());
            }
        }
        return reviewDtoList;
    }

    // 3. 리뷰 삭제 (비밀번호 기반)
    public boolean reviewDelete(int rid, String rpw) {
        Optional<ReviewEntity> optional = reviewEntityRepository.findById(rid);
        if (optional.isPresent()) {
            ReviewEntity reviewEntity = optional.get();
            if (reviewEntity.getRpw().equals(rpw)) {
                reviewEntityRepository.deleteById(rid);
                return true;
            }
        }
        return false;
    }
}
