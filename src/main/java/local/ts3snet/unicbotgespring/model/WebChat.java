package local.ts3snet.unicbotgespring.model;

import local.ts3snet.unicbotgespring.config.WebConfig;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class WebChat {
    private List<WebChatNode> chatNodes = new ArrayList<>();

    private WebConfig webUrl;
    @Autowired
    private void setWebUrl(WebConfig webUrl) {
        this.webUrl = webUrl;
    }

    private WebChatParser parser;
    @Autowired
    private void setWebChatParser(WebChatParser parser) {
        this.parser = parser;
    }

    public List<WebChatNode> update() {
        List<WebChatNode> newMessages = new ArrayList<>();
        List<WebChatNode> parseNodes = parser.parser(webUrl.getUrl());
        if (chatNodes.isEmpty()) {
            chatNodes = parseNodes;
            return parseNodes;
        }
        for (WebChatNode node : parseNodes) {
            if (chatNodes.get(chatNodes.size()-1).getId() < node.getId()) {
                chatNodes.add(node);
                //sendMessage(node.getId() + "-" + node.getMessage());
                newMessages.add(0, node);
                chatNodes.remove(0);
            }
        }
        return newMessages;
    }
}
