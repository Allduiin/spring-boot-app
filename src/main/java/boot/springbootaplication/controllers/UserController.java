package boot.springbootaplication.controllers;

import boot.springbootaplication.mapper.UserMapper;
import boot.springbootaplication.model.User;
import boot.springbootaplication.model.dto.UserResponseDto;
import boot.springbootaplication.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserResponseDto> getMostActiveUsers(
            @RequestParam(defaultValue = "1000") int limit,
            @RequestParam(defaultValue = "0") int offset) {
        List<User> mostActiveUsers = userService.getMostActiveUsers(limit, offset);
        return mostActiveUsers.stream()
                .map(userMapper::convertToResponseDto)
                .collect(Collectors.toList());
    }
}
