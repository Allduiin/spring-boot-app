package boot.springbootaplication.service.impl;

import boot.springbootaplication.service.FileReaderService;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

@Service
public class FileReaderServiceImpl implements FileReaderService {
    private static final String[] HEADERS = {"Id", "ProductId", "UserId", "ProfileName",
            "HelpfulnessNumerator", "HelpfulnessDenominator", "Score", "Time",
            "Summary", "Text"};

    @Override
    public Iterable<CSVRecord> readFromFile(String path) throws IOException {
        Reader in = new FileReader(path);
        return CSVFormat.DEFAULT
                .withHeader(HEADERS)
                .withFirstRecordAsHeader()
                .parse(in);
    }
}
