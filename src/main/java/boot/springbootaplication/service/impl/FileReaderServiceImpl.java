package boot.springbootaplication.service.impl;

import boot.springbootaplication.service.FileReaderService;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FileReaderServiceImpl implements FileReaderService {
    @Override
    public List<String> readFromFile(String path) throws IOException {
        List<String> result = new ArrayList<>();
        String row;
        try (BufferedReader csvReader = new BufferedReader(new FileReader(path))) {
            while ((row = csvReader.readLine()) != null) {
                result.add(row);
            }
        }
        return result;
    }
}
