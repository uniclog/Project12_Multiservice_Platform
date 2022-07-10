package local.uniclog.mainframe.api;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class DataAccessController {
    public final ApplicationContext context;

    public void initiateAppShutdown(int returnCode) {
        System.exit(SpringApplication.exit(context, () -> returnCode));
    }

    @GetMapping("/shutdown")
    public void shutdown() {
        initiateAppShutdown(0);
    }
}
