package boot.springbootaplication.service;

import java.io.IOException;
import java.util.List;

public interface FileParserService {

    boolean parseToFile(String path, List<String[]> data) throws IOException;
}
