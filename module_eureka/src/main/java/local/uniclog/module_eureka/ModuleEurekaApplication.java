package local.uniclog.module_eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka Server Starter
 *
 * @version 0.1
 */
@EnableEurekaServer
@SpringBootApplication
public class ModuleEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleEurekaApplication.class, args);
    }

}
