package local.ts3snet.unicbot_ms_spring.module_teamspeak.model;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@Component
@Scope("prototype")
public class CommandLet {
    private MessageType cmd = MessageType.DEFAULT;
    private String firstParameter = MessageType.DEFAULT.toString();
    private String allMessage = MessageType.DEFAULT.toString();

    /**
     * Find /command pattern in line
     * @param msg source message
     */
    public void update(String msg) {
        String regex = "(?<cmd>!\\w+)(\\s+)(?<value>\\w+)";
        Matcher matcher = Pattern.compile(regex).matcher(msg);
        if (matcher.find()) {
            this.setCmd(MessageType.getType(matcher.group("cmd")) );
            this.setFirstParameter( matcher.group("value") );
        } else this.setCmd( MessageType.DEFAULT );

        regex = "(?<cmd>!\\w+)(\\s+)(?<value>.*)";
        matcher = Pattern.compile(regex).matcher(msg);
        this.setAllMessage(matcher.find() ? matcher.group("value") : MessageType.DEFAULT.toString());

        if (this.getCmd().equals(MessageType.DEFAULT)) {
            regex = "(?<cmd>!\\w+)";
            matcher = Pattern.compile(regex).matcher(msg);
            this.setCmd(matcher.find() ? MessageType.getType(matcher.group("cmd")) : MessageType.DEFAULT);
        }

    }
}
