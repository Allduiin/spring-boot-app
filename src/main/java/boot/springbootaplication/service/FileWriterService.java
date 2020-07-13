package boot.springbootaplication.service;

import java.io.IOException;
import java.util.List;

public interface FileWriterService {

    boolean writeToFile(String path, List<String> data) throws IOException;
}
