package boot.springbootaplication.service.impl;

import boot.springbootaplication.mapper.UserMapper;
import boot.springbootaplication.model.Review;
import boot.springbootaplication.model.User;
import boot.springbootaplication.repositories.ReviewRepository;
import boot.springbootaplication.service.ReviewService;
import boot.springbootaplication.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public Review save(Review review) {
        if (userService.existsByProfileName(review.getProfileName())) {
            review.setUser(userService.findByProfileName(review.getProfileName()));
            return reviewRepository.saveAndFlush(review);
        } else {
            Review savedReview = reviewRepository.saveAndFlush(review);
            User user = userMapper.createUserFromReview(review);
            user = userService.save(user);
            savedReview.setUser(user);
            reviewRepository.save(savedReview);
            return savedReview;
        }
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
