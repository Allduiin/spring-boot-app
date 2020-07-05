package boot.springbootaplication;

import boot.springbootaplication.service.FileParserService;
import boot.springbootaplication.service.impl.FileParserServiceImpl;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.TemporaryFolder;

public class FileParserServiceTest {
    private final String TEST_FILE_NAME = "parseTest.csv";
    private final String TEST_FILE_CONTENT = "Id,ProductId,UserId,ProfileName,HelpfulnessNumerator,"
            + "HelpfulnessDenominator,Score,Time,Summary,Text";
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private final FileParserService fileParserService = new FileParserServiceImpl();

    @Test
    public void parseFromFileTest() throws IOException {
        ArrayList<String[]> testData = new ArrayList<>();
        testData.add(TEST_FILE_CONTENT.split(","));
        folder.create();
        File file = folder.newFile(TEST_FILE_NAME);
        BufferedReader csvReader = new BufferedReader(new FileReader(file.getPath()));
        fileParserService.parseToFile(file.getPath(), testData);
        List<String[]> result = new ArrayList<>();
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            result.add(data);
        }
        csvReader.close();
        Assert.assertArrayEquals(testData.toArray(), result.toArray());
        folder.delete();
    }
}
