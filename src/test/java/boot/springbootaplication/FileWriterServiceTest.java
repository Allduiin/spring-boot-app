package boot.springbootaplication;

import boot.springbootaplication.service.FileWriterService;
import boot.springbootaplication.service.impl.FileWriterServiceImpl;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.TemporaryFolder;

import static org.junit.Assert.fail;

public class FileWriterServiceTest {
    private final String TEST_FILE_NAME = "writeTest.csv";
    private final String TEST_FILE_CONTENT = "Id,ProductId,UserId,ProfileName,HelpfulnessNumerator,"
            + "HelpfulnessDenominator,Score,Time,Summary,Text";
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private final FileWriterService fileWriterService = new FileWriterServiceImpl();

    @Test
    public void writeToFileTest() throws IOException {
        ArrayList<String> testData = new ArrayList<>();
        testData.add(TEST_FILE_CONTENT);
        folder.create();
        File file = folder.newFile(TEST_FILE_NAME);
        BufferedReader csvReader = new BufferedReader(new FileReader(file.getPath()));
        fileWriterService.writeToFile(file.getPath(), testData);
        List<String> result = new ArrayList<>();
        String row;
        while ((row = csvReader.readLine()) != null) {
            result.add(row.substring(1,row.length() - 1));
        }
        csvReader.close();
        Assert.assertArrayEquals(testData.toArray(), result.toArray());
        folder.delete();
    }

    @Test
    public void writeToNotExistedFileTest() throws IOException {
        ArrayList<String> testData = new ArrayList<>();
        try {
            fileWriterService.writeToFile("Not Exist File", testData);
        } catch (FileNotFoundException e) {
            return;
        }
        fail("Write method must throw exception to unexist path");
    }

    @Test
    public void writeToNullPathTest() throws IOException {
        ArrayList<String> testData = new ArrayList<>();
        try {
            fileWriterService.writeToFile(null, testData);
        } catch (NullPointerException e) {
            return;
        }
        fail("Write method must throw NullPointerException to null path");
    }
}
