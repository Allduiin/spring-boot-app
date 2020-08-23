package boot.springbootaplication.service;

import boot.springbootaplication.model.Review;
import java.util.List;

public interface ProductService {
    List<Review> findMostReviewedProducts(int limit, int offset);
}
