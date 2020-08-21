package boot.springbootaplication.repositories;

import boot.springbootaplication.model.Product;
import boot.springbootaplication.model.Review;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT new products (r.productId, COUNT(reviews.productId)) "
            + "FROM reviews r GROUP BY r.productId ORDER BY COUNT(reviews.productId) DESC")
    List<Product> findMostReviewedProducts(PageRequest pageRequest);
}
