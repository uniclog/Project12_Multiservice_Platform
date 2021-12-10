package local.uniclog.mainframe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UniclogMainFrameApplication {
    public static void main(String[] args) {
        SpringApplication.run(UniclogMainFrameApplication.class, args);
    }
}
