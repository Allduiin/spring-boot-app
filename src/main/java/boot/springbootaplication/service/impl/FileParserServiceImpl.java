package boot.springbootaplication.service.impl;

import boot.springbootaplication.service.FileParserService;
import boot.springbootaplication.service.FileReaderService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileParserServiceImpl implements FileParserService {

    @Override
    public boolean parseToFile(String path, List<String[]> data) throws IOException {
        PrintWriter printWriter = new PrintWriter(path);
        for (String[] strings: data) {
            for (int i = 0; i < strings.length - 1; i++) {
                printWriter.print(strings[i]);
                printWriter.print(",");
            }
            printWriter.print(strings[strings.length - 1]);
            printWriter.print("\n");
        }
        printWriter.close();
        return true;
    }
}
