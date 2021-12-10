package local.ts3snet.unicbot_ms_spring.module_webutils.service_myfitness.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Slf4j
@Component
public class MyFitnessMessagesGenerator {
    public String generate(List<String> list) {
        return list.get(new Random().nextInt(list.size()));
    }
}
