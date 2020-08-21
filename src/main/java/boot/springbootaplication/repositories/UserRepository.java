package boot.springbootaplication.repositories;

import boot.springbootaplication.model.User;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT new users (u.profileName, size(u.reviews)) "
            + "FROM users u ORDER BY SIZE(u.reviews) DESC")
    List<User> findAllMostActive(PageRequest pageRequest);
}
