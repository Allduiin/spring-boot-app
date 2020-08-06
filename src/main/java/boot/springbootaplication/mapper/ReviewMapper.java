package boot.springbootaplication.mapper;

import boot.springbootaplication.model.Review;
import boot.springbootaplication.model.dto.ReviewFromFileDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public List<Review> getReviewFromDto(List<ReviewFromFileDto> reviews) {
        List<Review> result = new ArrayList<>();
        for (ReviewFromFileDto dto : reviews) {
            Review review = new Review();
            review.setProductId(dto.getProductId());
            review.setUserId(dto.getUserId());
            review.setProfileName(dto.getProfileName());
            review.setHelpfulnessNumerator(dto.getHelpfulnessNumerator());
            review.setHelpfulnessDenominator(dto.getHelpfulnessDenominator());
            review.setScore(dto.getScore());
            review.setTime(dto.getTime());
            review.setSummary(dto.getSummary());
            review.setText(dto.getText());
            result.add(review);
        }
        return result;
    }
}
