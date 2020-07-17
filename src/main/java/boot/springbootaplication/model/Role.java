package boot.springbootaplication.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Role {
    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    enum RoleName{
        ADMIN, USER
    }
}
