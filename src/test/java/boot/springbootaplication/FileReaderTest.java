package boot.springbootaplication;

import boot.springbootaplication.service.FileReaderService;
import boot.springbootaplication.service.impl.FileReaderServiceImpl;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.junit.jupiter.api.Assertions.fail;

public class FileReaderTest {
    private static final String[] HEADERS = {"Id", "ProductId", "UserId", "ProfileName",
            "HelpfulnessNumerator", "HelpfulnessDenominator", "Score", "Time",
            "Summary", "Text"};
    private static final String TEST_FILE_NAME = "readTest.csv";
    private static final String TEST_FILE_CONTENT1 = "Id,ProductId,UserId,ProfileName,HelpfulnessNumerator,"
            + "HelpfulnessDenominator,Score,Time,Summary,Text";
    private static final String TEST_FILE_CONTENT2 = "1,B001E4KFG0,A3SGXH7AUHU8GW,delmartian,1,1,5,1303862400,Go,od";
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private final FileReaderService fileReaderService = new FileReaderServiceImpl();

    @Test
    public void readFromFileTest() throws IOException {
        folder.create();
        File file = folder.newFile(TEST_FILE_NAME);
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.print(TEST_FILE_CONTENT1 + "\n");
        printWriter.print(TEST_FILE_CONTENT2);
        printWriter.close();
        List<CSVRecord> assertedRecords = new ArrayList<>();
        CSVFormat.DEFAULT
                .withHeader(HEADERS)
                .withFirstRecordAsHeader()
                .parse(new FileReader(file.getPath()))
                .forEach(assertedRecords::add);
        List<CSVRecord> realRecords = new ArrayList<>();
        fileReaderService.readFromFile(file.getPath()).forEach(realRecords::add);
        Assert.assertEquals("FileReader must correctly read from file",
                assertedRecords.toString(),
                realRecords.toString());
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
