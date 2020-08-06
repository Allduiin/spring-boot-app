package boot.springbootaplication;

import boot.springbootaplication.model.dto.ReviewFromFileDto;
import boot.springbootaplication.service.FileParserService;
import boot.springbootaplication.service.impl.FileParserServiceImpl;
import boot.springbootaplication.service.impl.FileReaderServiceImpl;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.csv.CSVRecord;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.junit.Assert.fail;

public class FileParserTest {
    private static final String TEST_FILE_NAME = "parseTest.csv";
    private static final String TEST_FILE_CONTENT1 = "Id,ProductId,UserId,ProfileName,HelpfulnessNumerator"
            + ",HelpfulnessDenominator,Score,Time,Summary,Text";
    private final FileParserService fileParserService = new FileParserServiceImpl();
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();


    @Test(expected = NullPointerException.class)
    public void emptyFileParserTest() {
        Assert.assertEquals("FileParser must correctly parse scv data file",
                Collections.emptyList(), fileParserService.parse(null));
    }

    @Test
    public void goodFileParserTest() throws IOException {
        final String testFileContent2 = "1,B001E4KFG0,A3SGXH7AUHU8GW,delmartian,1,1,5," +
                "1303862400,Good Quality Dog Food,I have bought several\",\" of the Vitality canned dog";
        folder.create();
        File file = folder.newFile(TEST_FILE_NAME);
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.print(TEST_FILE_CONTENT1 + "\n");
        printWriter.print(testFileContent2);
        printWriter.close();

        Iterable<CSVRecord> csvRecords = new FileReaderServiceImpl().readFromFile(file.getPath());
        List<ReviewFromFileDto> reviews = new ArrayList<>();
        ReviewFromFileDto review = new ReviewFromFileDto();
        review.setProductId("B001E4KFG0");
        review.setUserId("A3SGXH7AUHU8GW");
        review.setProfileName("delmartian");
        review.setHelpfulnessNumerator(1);
        review.setHelpfulnessDenominator(1);
        review.setScore((byte) 5);
        review.setTime(1303862400L);
        review.setSummary("Good Quality Dog Food");
        review.setText("I have bought several of the Vitality canned dog");
        reviews.add(review);
        Assert.assertEquals("FileParser must correctly parse scv data file",
                reviews, fileParserService.parse(csvRecords));
    }

    @Test
    public void nullFileParserTest() {
        try {
            fileParserService.parse(null);
        } catch (NullPointerException e) {
            return;
        }
        fail("Parser must throw NullPointerException when strings are null");
    }
}
