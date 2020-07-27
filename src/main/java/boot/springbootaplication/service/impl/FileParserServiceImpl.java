package boot.springbootaplication.service.impl;

import boot.springbootaplication.mapper.ReviewMapper;
import boot.springbootaplication.model.dto.ReviewFromFileDto;
import boot.springbootaplication.service.FileParserService;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FileParserServiceImpl implements FileParserService {
    private final ReviewMapper reviewMapper;

    public FileParserServiceImpl(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    @Override
    public List<ReviewFromFileDto> parse(List<String> reviewStrings) {
        List<ReviewFromFileDto> reviewDtoList = new ArrayList<>();
        CsvParserSettings csvParserSettings = new CsvParserSettings();
        CsvParser csvParser = new CsvParser(csvParserSettings);
        for (int i = 1; i < reviewStrings.size(); i++) {
            String[] reviewContent = csvParser.parseLine(reviewStrings.get(i));
            reviewDtoList.add(reviewMapper.getReviewDtoFromLine(reviewContent));
        }
        return reviewDtoList;
    }
}
