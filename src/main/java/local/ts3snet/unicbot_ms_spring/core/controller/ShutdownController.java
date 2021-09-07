package local.ts3snet.unicbot_ms_spring.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShutdownController {
    private ApplicationContext context;
    @Autowired
    public void setApplicationContext(ApplicationContext context) {
        this.context = context;
    }

    public void initiateAppShutdown(int returnCode) {
        System.exit(SpringApplication.exit(context, () -> returnCode));
    }

    @GetMapping("/shutdown")
    public void shutdown() {
        initiateAppShutdown(0);
    }
}