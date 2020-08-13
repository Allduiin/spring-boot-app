package boot.springbootaplication.service;

import boot.springbootaplication.model.dto.ReviewRequestDto;
import java.io.IOException;
import java.util.List;

public interface CsvParserService {
    List<ReviewRequestDto> readFromFile(String path) throws IOException;
}
