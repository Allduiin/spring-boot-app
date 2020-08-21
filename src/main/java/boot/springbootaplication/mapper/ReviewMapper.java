package boot.springbootaplication.mapper;

import boot.springbootaplication.model.Review;
import boot.springbootaplication.model.dto.ReviewRequestDto;
import boot.springbootaplication.model.dto.ReviewResponseDto;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReviewMapper {
    public List<Review> getReviewsFromDto(List<ReviewRequestDto> reviews) {
        List<Review> result = new ArrayList<>();
        for (ReviewRequestDto dto : reviews) {
            result.add(getReviewFromReviewRequestDto(dto));
        }
        return result;
    }

    public Review getReviewFromReviewRequestDto(ReviewRequestDto dto) {
        Review review = new Review();
        review.setProductId(dto.getProductId());
        review.setProfileName(dto.getProfileName());
        review.setHelpfulnessNumerator(dto.getHelpfulnessNumerator());
        review.setHelpfulnessDenominator(dto.getHelpfulnessDenominator());
        review.setScore(dto.getScore());
        review.setTime(dto.getTime());
        review.setSummary(dto.getSummary());
        review.setText(dto.getText());
        return review;
    }

    public ReviewResponseDto getReviewResponseDtoFromReview(Review review) {
        ReviewResponseDto dto = new ReviewResponseDto();
        dto.setId(review.getId());
        dto.setProductId(review.getProductId());
        dto.setUserIdFromFile(review.getUserIdFromFile());
        dto.setProfileName(review.getProfileName());
        dto.setHelpfulnessNumerator(review.getHelpfulnessNumerator());
        dto.setHelpfulnessDenominator(review.getHelpfulnessDenominator());
        dto.setScore(review.getScore());
        dto.setTime(review.getTime());
        dto.setSummary(review.getSummary());
        dto.setText(review.getText());
        dto.setUserId(review.getUser().getId());
        return dto;
    }
}
