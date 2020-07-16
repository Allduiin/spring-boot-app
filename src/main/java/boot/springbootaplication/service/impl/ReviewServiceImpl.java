package boot.springbootaplication.service.impl;

import boot.springbootaplication.model.Review;
import boot.springbootaplication.repositories.ReviewRepository;
import boot.springbootaplication.service.ReviewService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review getById(Long id) {
        return reviewRepository.getOne(id);
    }

    @Override
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }
}
