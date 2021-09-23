package local.ts3snet.unicbot_ms_spring.module_webutils.service_ekey.model;

import local.ts3snet.unicbot_ms_spring.module_webutils.service_ekey.config.KeyModuleWebConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Web chat model
 * @author erlidione
 * @version 1.0
 */
@Slf4j
@Component
public class WebChat {
    private List<WebChatNode> chatNodes = new ArrayList<>();

    private WebChatParser parser;
    @Autowired
    private void setWebChatParser(WebChatParser parser) {
        this.parser = parser;
    }

    private final KeyModuleWebConfig keyModuleWebConfig;

    /**
     * Class Constructor
     * @param keyModuleWebConfig configuration (url, pattern, update flag)
     */
    public WebChat(KeyModuleWebConfig keyModuleWebConfig) {
        this.keyModuleWebConfig = keyModuleWebConfig;
    }

    /**
     * Key parse (key regex)
     * @param msg massage (text data)
     * @return list keys
     */
    public List<String> parseKey(String msg) {
        return parser.parseKey(msg, keyModuleWebConfig.getKeyPattern());
    }

    /**
     * Return new message list
     * @return new messages
     */
    public List<WebChatNode> update() {
        List<WebChatNode> newMessages = new ArrayList<>();
        if (Boolean.FALSE.equals(keyModuleWebConfig.getUpdateLoop()))
            return newMessages;
        List<WebChatNode> parseNodes = parser.messageParser(keyModuleWebConfig.getUrl());
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
