package boot.springbootaplication;

import boot.springbootaplication.model.dto.ReviewRequestDto;
import boot.springbootaplication.service.CsvParserService;
import boot.springbootaplication.service.impl.CsvParserServiceImpl;
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
import org.junit.jupiter.api.Assertions;
import org.junit.rules.TemporaryFolder;

public class CsvParserTest {
    private static final String TEST_FILE_NAME = "parseTest.csv";
    private static final CsvParserService csvParserService = new CsvParserServiceImpl();
    private static final String[] HEADERS = {"Id", "ProductId", "UserId", "ProfileName",
            "HelpfulnessNumerator", "HelpfulnessDenominator", "Score", "Time",
            "Summary", "Text"};
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void readFromFileTest() throws IOException {
        folder.create();
        File file = folder.newFile(TEST_FILE_NAME);
        List<String[]> dataLines = new ArrayList<>();
        dataLines.add(HEADERS);
        dataLines.add(new String[]
                {"1", "B001E4KFG0", "A3SGXH7AUHU8GW", "delmartian", "1", "1", "5",
                        "1303862400", "Good Quality Dog Food", "I have bought several of the Vitality"});
        try (PrintWriter pw = new PrintWriter(file)) {
            dataLines.stream()
                    .map(data -> String.join(",", data))
                    .forEach(pw::println);
        }

        List<ReviewRequestDto> assertedRecords = new ArrayList<>();
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader(HEADERS)
                .withFirstRecordAsHeader()
                .parse(new FileReader(file.getPath()));
        ReviewRequestDto recordDto;
        for (CSVRecord record : records) {
            recordDto = new ReviewRequestDto();
            recordDto.setProductId(record.get("ProductId"));
            recordDto.setUserIdFromFile(record.get("UserId"));
            recordDto.setProfileName(record.get("ProfileName"));
            recordDto.setHelpfulnessNumerator(Integer
                    .parseInt(record.get("HelpfulnessNumerator")));
            recordDto.setHelpfulnessDenominator(Integer
                    .parseInt(record.get("HelpfulnessDenominator")));
            recordDto.setScore(Byte.parseByte(record.get("Score")));
            recordDto.setTime(Long.parseLong(record.get("Time")));
            recordDto.setSummary(record.get("Summary"));
            recordDto.setText(record.get("Text"));
            assertedRecords.add(recordDto);
        }

        List<ReviewRequestDto> realRecords = csvParserService.readFromFile(file.getPath());
        Assert.assertEquals("FileReader must correctly read from file",
                assertedRecords.toString(),
                realRecords.toString());
        folder.delete();
    }

    @Test
    public void tryToReadFromWrongPath() throws IOException {
        try {
            csvParserService.readFromFile("wrong path");
        } catch (FileNotFoundException e) {
            return;
        }
        Assertions.fail("Excepted FileNotFoundException when path is wrong");
    }

    @Test
    public void tryToReadFromNullPath() throws IOException {
        try {
            csvParserService.readFromFile(null);
        } catch (NullPointerException e) {
            return;
        }
        Assertions.fail("Excepted NullPointerException when path is null");
    }
}
