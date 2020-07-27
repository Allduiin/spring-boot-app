package boot.springbootaplication.mapper;

import boot.springbootaplication.model.Review;
import boot.springbootaplication.model.dto.ReviewFromFileDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    private static final int PRODUCT_ID_POSITION = 1;
    private static final int USER_ID_POSITION = 2;
    private static final int PROFILE_NAME_POSITION = 3;
    private static final int HELPFULNESS_NOMINATOR_POSITION = 4;
    private static final int HELPFULNESS_DENOMINATOR_POSITION = 5;
    private static final int SCORE_POSITION = 6;
    private static final int TIME_POSITION = 7;
    private static final int SUMMARY_POSITION = 8;
    private static final int TEXT_POSITION = 9;

    public ReviewFromFileDto getReviewDtoFromLine(String[] line) {
        ReviewFromFileDto reviewDto = new ReviewFromFileDto();
        reviewDto.setProductId(line[PRODUCT_ID_POSITION]);
        reviewDto.setUserId(line[USER_ID_POSITION]);
        reviewDto.setProfileName(line[PROFILE_NAME_POSITION]);
        reviewDto.setHelpfulnessNumerator(Integer
                .valueOf(line[HELPFULNESS_NOMINATOR_POSITION]));
        reviewDto.setHelpfulnessDenominator(Integer
                .valueOf(line[HELPFULNESS_DENOMINATOR_POSITION]));
        reviewDto.setScore(Byte.valueOf(line[SCORE_POSITION]));
        reviewDto.setTime(Long
                .valueOf(line[TIME_POSITION]));
        reviewDto.setSummary(line[SUMMARY_POSITION]);
        reviewDto.setText(line[TEXT_POSITION]);
        return reviewDto;
    }

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
