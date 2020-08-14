package boot.springbootaplication.service.impl;

import boot.springbootaplication.model.dto.ReviewFromFileDto;
import boot.springbootaplication.service.CsvParserService;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

@Service
public class CsvParserServiceImpl implements CsvParserService {
    private static final String[] HEADERS = {"Id", "ProductId", "UserId", "ProfileName",
            "HelpfulnessNumerator", "HelpfulnessDenominator", "Score", "Time",
            "Summary", "Text"};

    @Override
    public List<ReviewFromFileDto> readFromFile(String path) throws IOException {
        Reader in = new FileReader(path);
        return parse(CSVFormat.DEFAULT
                .withHeader(HEADERS)
                .withFirstRecordAsHeader()
                .parse(in));
    }

    private List<ReviewFromFileDto> parse(Iterable<CSVRecord> records) {
        List<ReviewFromFileDto> recordDtos = new ArrayList<>();
        ReviewFromFileDto recordDto;
        for (CSVRecord record : records) {
            recordDto = new ReviewFromFileDto();
            recordDto.setProductId(record.get("ProductId"));
            recordDto.setUserId(record.get("UserId"));
            recordDto.setProfileName(record.get("ProfileName"));
            recordDto.setHelpfulnessNumerator(Integer
                    .parseInt(record.get("HelpfulnessNumerator")));
            recordDto.setHelpfulnessDenominator(Integer
                    .parseInt(record.get("HelpfulnessDenominator")));
            recordDto.setScore(Byte.parseByte(record.get("Score")));
            recordDto.setTime(Long.parseLong(record.get("Time")));
            recordDto.setSummary(record.get("Summary"));
            recordDto.setText(record.get("Text"));
            recordDtos.add(recordDto);
        }
        return recordDtos;
    }
}
