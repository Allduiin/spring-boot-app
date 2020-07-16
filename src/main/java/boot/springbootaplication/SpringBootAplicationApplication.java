package boot.springbootaplication;

import boot.springbootaplication.model.User;
import boot.springbootaplication.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootAplicationApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringBootAplicationApplication.class, args);
        User user = new User();
        UserService userService = run.getBean(UserService.class);
        user.setIdFromFile("AD1SAD5");
        user.setProfileName("Vasa");
        user.setPassword("1234");
        userService.save(user);
        System.out.println("User was added to db");
    }
}
