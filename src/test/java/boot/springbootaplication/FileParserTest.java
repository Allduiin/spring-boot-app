package boot.springbootaplication;

import boot.springbootaplication.model.dto.ReviewFromFileDto;
import boot.springbootaplication.service.FileParserService;
import boot.springbootaplication.service.impl.FileParserServiceImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.fail;

public class FileParserTest {
    private static final String TEST_FILE_CONTENT1 = "id,userId";
    private final FileParserService fileParserService = new FileParserServiceImpl();

    @Test
    public void emptyFileParserTest() {
        Assert.assertEquals("FileParser must correctly parse scv data file",
                Collections.emptyList(), fileParserService.parse(List.of(TEST_FILE_CONTENT1)));
    }

    @Test
    public void goodFileParserTest() {
        final String testFileContent2 = "1,B001E4KFG0,A3SGXH7AUHU8GW,delmartian,1,1,5," +
                "1303862400,Good Quality Dog Food,I have bought several of the Vitality canned dog";
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
                reviews, fileParserService.parse(List.of(TEST_FILE_CONTENT1, testFileContent2)));
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
