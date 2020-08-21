package boot.springbootaplication.model.dto;

import lombok.Data;

@Data
public class ReviewRequestDto {
    private String productId;
    private String userIdFromFile;
    private String profileName;
    private Integer helpfulnessNumerator;
    private Integer helpfulnessDenominator;
    private Byte score;
    private Long time;
    private String summary;
    private String text;
    private Long userId;
}
