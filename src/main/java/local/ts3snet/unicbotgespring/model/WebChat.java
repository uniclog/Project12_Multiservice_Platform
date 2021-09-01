package local.ts3snet.unicbotgespring.model;

import local.ts3snet.unicbotgespring.config.WebConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class WebChat {
    private List<WebChatNode> chatNodes = new ArrayList<>();

    private final WebConfig webConfig;

    public WebChat(WebConfig webConfig) {
        this.webConfig = webConfig;
    }

    private WebChatParser parser;
    @Autowired
    private void setWebChatParser(WebChatParser parser) {
        this.parser = parser;
    }

    public List<String> parseKey(String text) {
        return parser.parceKey(text, webConfig.getKeyPattern());
    }

    public List<WebChatNode> update() {
        List<WebChatNode> newMessages = new ArrayList<>();
        if (Boolean.FALSE.equals(webConfig.getUpdateLoop()))
            return newMessages;
        List<WebChatNode> parseNodes = parser.messageParser(webConfig.getUrl());
        if (chatNodes.isEmpty()) {
            chatNodes = parseNodes;
            return parseNodes;
        }
        for (WebChatNode node : parseNodes) {
            if (chatNodes.get(chatNodes.size()-1).getId() < node.getId()) {
                chatNodes.add(node);
                newMessages.add(0, node);
                chatNodes.remove(0);
            }
        }
        return newMessages;
    }
}
