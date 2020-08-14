package boot.springbootaplication;

import boot.springbootaplication.model.Role;
import boot.springbootaplication.service.RoleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootAplicationApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringBootAplicationApplication.class, args);
        RoleService roleService = run.getBean(RoleService.class);
        roleService.save(new Role(Role.RoleName.USER));
        roleService.save(new Role(Role.RoleName.ADMIN));
    }
}
