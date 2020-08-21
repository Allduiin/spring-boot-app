package boot.springbootaplication.repositories;

import boot.springbootaplication.model.User;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select new users(u.profileName, size(u.reviews)) from users u order by size(u.reviews) desc")
    List<User> findAllMostActive(PageRequest pageRequest);
}
