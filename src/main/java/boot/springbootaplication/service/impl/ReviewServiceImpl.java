package boot.springbootaplication.service.impl;

import boot.springbootaplication.model.Review;
import boot.springbootaplication.model.Role;
import boot.springbootaplication.model.User;
import boot.springbootaplication.repositories.ReviewRepository;
import boot.springbootaplication.service.ReviewService;
import boot.springbootaplication.service.RoleService;
import boot.springbootaplication.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private static final String USER_PASSWORD = "1234";
    private final RoleService roleService;
    private final ReviewRepository reviewRepository;
    private final UserService userService;

    @Override
    public Review save(Review review) {
        Review savedReview = reviewRepository.saveAndFlush(review);
        if (userService.existsByProfileName(review.getProfileName())) {
            userService.findByProfileName(review.getProfileName()).getReviews().add(review);
        } else {
            User user = new User();
            user.setProfileName(review.getProfileName());
            user.setPassword(USER_PASSWORD);
            user.setIdFromFile(review.getUserIdFromFile());
            user.setReviews(List.of(savedReview));
            user.setRoles(List.of(roleService.getByRoleName(Role.RoleName.USER)));
            userService.save(user);
        }
        return savedReview;
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
