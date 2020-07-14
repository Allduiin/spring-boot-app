package boot.springbootaplication;

import boot.springbootaplication.service.FileReaderService;
import boot.springbootaplication.service.impl.FileReaderServiceImpl;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.junit.jupiter.api.Assertions.fail;

public class FileReaderTest {
    private final String TEST_FILE_NAME = "readTest.csv";
    private final String TEST_FILE_CONTENT1 = "Id,ProductId,UserId,ProfileName,HelpfulnessNumerator,"
            + "HelpfulnessDenominator,Score,Time,Summary,Text";
    private final String TEST_FILE_CONTENT2 = "1,B001E4KFG0,A3SGXH7AUHU8GW,delmartian,1,1,5,1303862400,Good";
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private FileReaderService fileReaderService = new FileReaderServiceImpl();

    @Test
    public void readFromFileTest() throws IOException {
        folder.create();
        File file = folder.newFile(TEST_FILE_NAME);
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.print(TEST_FILE_CONTENT1 + "\n");
        printWriter.print(TEST_FILE_CONTENT2);
        printWriter.close();
        Assert.assertArrayEquals("FileReader must correctly read from file",
                List.of(TEST_FILE_CONTENT1, TEST_FILE_CONTENT2).toArray(),
                fileReaderService.readFromFile(file.getPath()).toArray());
        folder.delete();
    }

    @Test
    public void tryToReadFromWrongPath() throws IOException {
        try {
            fileReaderService.readFromFile("wrong path");
        } catch (FileNotFoundException e) {
            return;
        }
        fail("Excepted FileNotFoundException when path is wrong");
    }

    @Test
    public void tryToReadFromNullPath() throws IOException {
        try {
            fileReaderService.readFromFile(null);
        } catch (NullPointerException e) {
            return;
        }
        fail("Excepted NullPointerException when path is null");
    }
}
