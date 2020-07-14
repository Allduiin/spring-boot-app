package boot.springbootaplication.service.impl;

import boot.springbootaplication.model.ReviewFromFileDto;
import boot.springbootaplication.service.FileParserService;
import com.sun.xml.bind.v2.model.core.ID;
import java.util.ArrayList;
import java.util.List;

public class FileParserServiceImpl implements FileParserService {
    private static final int ID_INDEX = 0;
    private static final int PRODUCT_ID_INDEX = 1;
    private static final int USER_ID_INDEX = 2;
    private static final int PROFILE_NAME_INDEX = 3;
    private static final int HELPFULNESS_NUMERATOR_INDEX = 4;
    private static final int HELPFULNESS_DENOMINATOR_INDEX = 5;
    private static final int SCORE_INDEX = 6;
    private static final int TIME_INDEX = 7;
    private static final int SUMMARY_INDEX = 8;
    private static final int TEXT_INDEX = 9;

    @Override
    public List<ReviewFromFileDto> parse(List<String> reviewStrings) {
        List<ReviewFromFileDto> result = new ArrayList<>();
        for (int i = 1; i < reviewStrings.size(); i++) {
            String[] params = reviewStrings.get(i).split(",");
            ReviewFromFileDto review = new ReviewFromFileDto();
            review.setId(Long.parseLong(params[ID_INDEX]));
            review.setProductId(params[PRODUCT_ID_INDEX]);
            review.setUserId(params[USER_ID_INDEX]);
            review.setProfileName(params[PROFILE_NAME_INDEX]);
            review.setHelpfulnessNumerator(Integer.parseInt(params[HELPFULNESS_NUMERATOR_INDEX]));
            review.setHelpfulnessDenominator(Integer.parseInt(params[HELPFULNESS_DENOMINATOR_INDEX]));
            review.setScore(Byte.parseByte(params[SCORE_INDEX]));
            review.setTime(Long.parseLong(params[TIME_INDEX]));
            review.setSummary(params[SUMMARY_INDEX]);
            review.setText(params[TEXT_INDEX]);
            result.add(review);
        }
        return result;
    }
}
