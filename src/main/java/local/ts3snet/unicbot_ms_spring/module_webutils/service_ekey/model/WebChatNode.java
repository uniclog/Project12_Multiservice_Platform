package local.ts3snet.unicbot_ms_spring.module_webutils.service_ekey.model;

import lombok.Data;

/**
 * Chat message model
 * @author erlidione
 */
@Data
public class WebChatNode {
    private Long id;
    private String author;
    private String time;
    private String message;

    @Override
    public String toString() {
        return id + " - " + message;
    }
}
