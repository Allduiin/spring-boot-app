package boot.springbootaplication;

import boot.springbootaplication.service.FileWorkService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootAplicationApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringBootAplicationApplication.class, args);
        run.getBean(FileWorkService.class).add("src\\main\\resources\\Reviews.csv");
    }
}
