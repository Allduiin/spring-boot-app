package boot.springbootaplication.service.impl;

import boot.springbootaplication.service.FileReaderService;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderServiceImpl implements FileReaderService {
    @Override
    public List<String> readFromFile(String path) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(path));
        List<String> result = new ArrayList<>();
        String row;
        while ((row = csvReader.readLine()) != null) {
            result.add(row);
        }
        csvReader.close();
        return result;
    }
}
