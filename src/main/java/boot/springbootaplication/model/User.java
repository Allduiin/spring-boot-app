package boot.springbootaplication.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idFromFile;
    private String profileName;
    private String password;
    @OneToMany
    private List<Review> reviews;
    @ManyToMany
    private List<Role> roles;
}
