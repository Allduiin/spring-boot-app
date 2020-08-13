package boot.springbootaplication.service;

import boot.springbootaplication.model.dto.ReviewFromFileDto;
import java.io.IOException;
import java.util.List;

public interface CsvParserService {
    List<ReviewFromFileDto> readFromFile(String path) throws IOException;
}
