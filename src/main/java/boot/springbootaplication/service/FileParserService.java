package boot.springbootaplication.service;

import boot.springbootaplication.model.dto.ReviewFromFileDto;
import java.util.List;
import org.apache.commons.csv.CSVRecord;

public interface FileParserService {
    List<ReviewFromFileDto> parse(Iterable<CSVRecord> records);
}
