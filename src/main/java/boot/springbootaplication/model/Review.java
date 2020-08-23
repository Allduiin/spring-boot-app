package boot.springbootaplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productId;
    private String userIdFromFile;
    private String profileName;
    private Integer helpfulnessNumerator;
    private Integer helpfulnessDenominator;
    private Byte score;
    private Long time;
    private String summary;
    @Column(length = 65000)
    private String text;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @Transient
    private Long numberOfReviews;

    public Review(String productId, Long numberOfReviews) {
        this.productId = productId;
        this.numberOfReviews = numberOfReviews;
    }
}
