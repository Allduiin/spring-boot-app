package boot.springbootaplication.service.impl;

import boot.springbootaplication.mapper.ReviewMapper;
import boot.springbootaplication.model.Review;
import boot.springbootaplication.model.Role;
import boot.springbootaplication.model.User;
import boot.springbootaplication.model.dto.ReviewFromFileDto;
import boot.springbootaplication.service.CsvParserService;
import boot.springbootaplication.service.FileWorkService;
import boot.springbootaplication.service.ReviewService;
import boot.springbootaplication.service.RoleService;
import boot.springbootaplication.service.UserService;
import java.io.IOException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FileWorkServiceImpl implements FileWorkService {
    private static final String USER_PASSWORD = "1234";
    private final CsvParserService fileParserService;
    private final ReviewMapper reviewMapper;
    private final ReviewService reviewService;
    private final UserService userService;
    private final RoleService roleService;

    @Override
    public boolean add(String path) {
        List<ReviewFromFileDto> dtos;
        try {
             dtos = fileParserService.readFromFile(path);
        } catch (IOException e) {
            throw new RuntimeException("IOException at fileReaderService", e);
        }
        List<Review> reviews = reviewMapper.getReviewFromDto(dtos);
        for (Review review : reviews) {
            Review savedReview = reviewService.save(review);
            if (userService.existsByProfileName(review.getProfileName())) {
                userService.findByProfileName(review.getProfileName()).getReviews().add(review);
            } else {
                User user = new User();
                user.setProfileName(review.getProfileName());
                user.setPassword(USER_PASSWORD);
                user.setIdFromFile(review.getUserId());
                user.setReviews(List.of(savedReview));
                user.setRoles(List.of(roleService.getByRoleName(Role.RoleName.USER)));
                userService.save(user);
            }
        }
        return true;
    }
}
