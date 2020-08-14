package boot.springbootaplication.mapper;

import boot.springbootaplication.model.User;
import boot.springbootaplication.model.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto convertToResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setProfileName(user.getProfileName());
        return dto;
    }
}
