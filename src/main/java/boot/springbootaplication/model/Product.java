package boot.springbootaplication.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Entity(name = "products")
public class Product {
    @Id
    @NonNull
    private String productId;
    private Long numberOfReviews;

    public Product(String productId, Long numberOfReviews) {
        this.productId = productId;
        this.numberOfReviews = numberOfReviews;
    }
}
