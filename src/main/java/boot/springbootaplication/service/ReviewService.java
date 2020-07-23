package boot.springbootaplication.service;

import boot.springbootaplication.model.Review;
import java.util.List;

public interface ReviewService {
    Review save(Review review);

    Review getById(Long id);

    List<Review> getAll();

    void delete(Long id);
}
