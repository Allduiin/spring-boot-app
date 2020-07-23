package boot.springbootaplication.service.impl;

import boot.springbootaplication.mapper.ReviewMapper;
import boot.springbootaplication.model.Review;
import boot.springbootaplication.model.Role;
import boot.springbootaplication.model.User;
import boot.springbootaplication.service.FileParserService;
import boot.springbootaplication.service.FileReaderService;
import boot.springbootaplication.service.FileWorkService;
import boot.springbootaplication.service.ReviewService;
import boot.springbootaplication.service.RoleService;
import boot.springbootaplication.service.UserService;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FileWorkServiceImpl implements FileWorkService {
    private static final String USER_PASSWORD = "1234";
    private final FileReaderService fileReaderService;
    private final FileParserService fileParserService;
    private final ReviewMapper reviewMapper;
    private final ReviewService reviewService;
    private final UserService userService;
    private final RoleService roleService;

    public FileWorkServiceImpl(FileReaderService fileReaderService,
                               FileParserService fileParserService, ReviewMapper reviewMapper,
                               ReviewService reviewService, UserService userService,
                               RoleService roleService) {
        this.fileReaderService = fileReaderService;
        this.fileParserService = fileParserService;
        this.reviewMapper = reviewMapper;
        this.reviewService = reviewService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public boolean add(String path) {
        List<String> strings;
        try {
            strings = fileReaderService.readFromFile(path);
        } catch (IOException e) {
            throw new RuntimeException("IOException at fileReaderService", e);
        }
        List<Review> reviews = reviewMapper.getReviewFromDto(fileParserService.parse(strings));
        for (Review review : reviews) {
            Review savedReview = reviewService.save(review);
            if (!userService.existsByProfileName(review.getProfileName())) {
                User user = new User();
                user.setProfileName(review.getProfileName());
                user.setPassword(USER_PASSWORD);
                user.setIdFromFile(review.getUserId());
                user.setReviews(List.of(savedReview));
                user.setRoles(List.of(roleService.getByRoleName(Role.RoleName.USER)));
                userService.save(user);
            } else {
                userService.findByProfileName(review.getProfileName()).getReviews().add(review);
            }
        }
        return true;
    }
}
