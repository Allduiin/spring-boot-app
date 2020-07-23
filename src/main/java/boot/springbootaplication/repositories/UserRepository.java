package boot.springbootaplication.repositories;

import boot.springbootaplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
