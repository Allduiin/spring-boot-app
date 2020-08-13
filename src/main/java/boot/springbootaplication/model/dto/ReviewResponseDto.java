package boot.springbootaplication.model.dto;

import lombok.Data;

@Data
public class ReviewResponseDto {
    private Long id;
    private String productId;
    private String userId;
    private String profileName;
    private Integer helpfulnessNumerator;
    private Integer helpfulnessDenominator;
    private Byte score;
    private Long time;
    private String summary;
    private String text;
}
