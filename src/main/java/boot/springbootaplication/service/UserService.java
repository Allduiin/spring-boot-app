package boot.springbootaplication.service;

import boot.springbootaplication.model.User;
import java.util.List;

public interface UserService {
    User save(User user);

    User getById(Long id);

    List<User> getAll();

    void delete(Long id);

    boolean existsByProfileName(String profileName);

    User findByProfileName(String profileName);
}
