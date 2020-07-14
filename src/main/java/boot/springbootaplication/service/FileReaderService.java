package boot.springbootaplication.service;

import java.io.IOException;
import java.util.List;

public interface FileReaderService {

    List<String> readFromFile(String path) throws IOException;
}
