package boot.springbootaplication.service.impl;

import boot.springbootaplication.model.User;
import boot.springbootaplication.repositories.UserRepository;
import boot.springbootaplication.service.UserService;
import java.util.List;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsByProfileName(String profileName) {
        User user = new User();
        user.setProfileName(profileName);
        return userRepository.exists(Example.of(user));
    }

    @Override
    public User findByProfileName(String profileName) {
        User user = new User();
        user.setProfileName(profileName);
        return userRepository.findOne(Example.of(user))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
