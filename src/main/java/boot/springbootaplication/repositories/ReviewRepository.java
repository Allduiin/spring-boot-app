package boot.springbootaplication.repositories;

import boot.springbootaplication.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
