package boot.springbootaplication.service;

import java.io.IOException;
import org.apache.commons.csv.CSVRecord;

public interface FileReaderService {
    Iterable<CSVRecord> readFromFile(String path) throws IOException;
}
