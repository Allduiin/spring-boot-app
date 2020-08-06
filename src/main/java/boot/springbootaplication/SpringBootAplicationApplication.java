package boot.springbootaplication;

import boot.springbootaplication.model.Role;
import boot.springbootaplication.service.FileWorkService;
import boot.springbootaplication.service.RoleService;
import boot.springbootaplication.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootAplicationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAplicationApplication.class, args);
    }
}
