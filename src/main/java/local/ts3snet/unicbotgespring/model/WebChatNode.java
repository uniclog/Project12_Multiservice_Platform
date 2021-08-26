package local.ts3snet.unicbotgespring.model;

import lombok.Data;

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
