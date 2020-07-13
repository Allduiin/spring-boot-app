package boot.springbootaplication.service.impl;

import boot.springbootaplication.model.ReviewFromFileDto;
import boot.springbootaplication.service.FileParserService;
import java.util.ArrayList;
import java.util.List;

public class FileParserServiceImpl implements FileParserService {
    @Override
    public List<ReviewFromFileDto> parse(List<String> reviewStrings) {
        List<ReviewFromFileDto> result = new ArrayList<>();
        for (int i = 1; i < reviewStrings.size(); i++) {
            String[] params = reviewStrings.get(i).split(",");
            ReviewFromFileDto review = new ReviewFromFileDto();
            review.setId(Long.parseLong(params[0]));
            review.setProductId(params[1]);
            review.setUserId(params[2]);
            review.setProfileName(params[3]);
            review.setHelpfulnessNumerator(Integer.parseInt(params[4]));
            review.setHelpfulnessDenominator(Integer.parseInt(params[5]));
            review.setScore(Byte.parseByte(params[6]));
            review.setTime(Long.parseLong(params[7]));
            review.setSummary(params[8]);
            review.setText(params[9]);
            result.add(review);
        }
        return result;
    }
}
