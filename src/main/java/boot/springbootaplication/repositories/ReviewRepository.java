package boot.springbootaplication.repositories;

import boot.springbootaplication.model.Review;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r.productId FROM reviews r ")
            /**, COUNT(r.productId)
            + "GROUP BY r.productId ORDER BY COUNT(r.productId) DESC")*/
    List<Object[]> findMostReviewedProducts(PageRequest pageRequest);
}
