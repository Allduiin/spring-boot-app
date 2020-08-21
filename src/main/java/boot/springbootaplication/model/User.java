package boot.springbootaplication.model;

import com.sun.istack.NotNull;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

@Data
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idFromFile;
    @NotNull
    @Column(unique = true)
    private String profileName;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Review> reviews;
    @ManyToMany
    private List<Role> roles;
    @Transient
    private int numberOfReviews;
}
