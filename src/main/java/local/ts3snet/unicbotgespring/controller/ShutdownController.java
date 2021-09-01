package local.ts3snet.unicbotgespring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShutdownController {
    @Autowired
    private ApplicationContext context;

    public void initiateAppShutdown(int returnCode) {
        //SpringApplication.exit(context, () -> returnCode);
        //System.exit(0);
        System.exit(SpringApplication.exit(context, () -> returnCode));
    }

    @GetMapping("/shutdown")
    public void shutdown() {
        initiateAppShutdown(0);
    }
}