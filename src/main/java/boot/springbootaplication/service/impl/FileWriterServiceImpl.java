package boot.springbootaplication.service.impl;

import boot.springbootaplication.service.FileWriterService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileWriterServiceImpl implements FileWriterService {

    @Override
    public boolean writeToFile(String path, List<String> data) throws IOException {
        if (!Files.exists(Path.of(path))) {
            throw new FileNotFoundException("File to write did't find");
        }
        PrintWriter printWriter = new PrintWriter(path);
        printWriter.print(data);
        printWriter.close();
        return true;
    }
}
