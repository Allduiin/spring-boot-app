package boot.springbootaplication.mapper;

import boot.springbootaplication.model.Review;
import boot.springbootaplication.model.Role;
import boot.springbootaplication.model.User;
import boot.springbootaplication.model.dto.UserResponseDto;
import boot.springbootaplication.service.RoleService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {
    private static final String USER_PASSWORD = "1234";
    private final RoleService roleService;

    public UserResponseDto convertToResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setProfileName(user.getProfileName());
        dto.setNumberOfReviews(user.getNumberOfReviews());
        return dto;
    }

    public User createUserFromReview(Review review) {
        User user = new User();
        user.setProfileName(review.getProfileName());
        user.setPassword(USER_PASSWORD);
        user.setIdFromFile(review.getUserIdFromFile());
        user.setReviews(List.of(review));
        user.setRoles(List.of(roleService.getByRoleName(Role.RoleName.USER)));
        return user;
    }
}
