package boot.springbootaplication.service;

import boot.springbootaplication.model.ReviewFromFileDto;
import java.util.List;

public interface FileParserService {
    List<ReviewFromFileDto> parse(List<String> reviewStrings);
}
