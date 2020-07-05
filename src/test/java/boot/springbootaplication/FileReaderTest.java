package boot.springbootaplication;

import boot.springbootaplication.service.FileReaderService;
import boot.springbootaplication.service.impl.FileReaderServiceImpl;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.TemporaryFolder;

public class FileReaderTest {
    private final String TEST_FILE_NAME = "readTest.csv";
    private final String TEST_FILE_CONTENT = "Id,ProductId,UserId,ProfileName,HelpfulnessNumerator,"
            + "HelpfulnessDenominator,Score,Time,Summary,Text";
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private FileReaderService fileReaderService = new FileReaderServiceImpl();

    @Test
    public void readFromFileTest() throws IOException {
        ArrayList<String[]> testData = new ArrayList<>();
        testData.add(TEST_FILE_CONTENT.split(","));
        folder.create();
        File file = folder.newFile(TEST_FILE_NAME);
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.print(TEST_FILE_CONTENT);
        printWriter.close();
        Assert.assertArrayEquals(testData.toArray(), fileReaderService.readFromFile(file.getPath()).toArray());
        folder.delete();
    }
}
