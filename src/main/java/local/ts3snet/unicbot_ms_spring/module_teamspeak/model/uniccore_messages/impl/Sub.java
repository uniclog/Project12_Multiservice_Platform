package local.ts3snet.unicbot_ms_spring.module_teamspeak.model.uniccore_messages.impl;

import local.ts3snet.unicbot_ms_spring.core.entity.TeamspeakUserEntity;
import local.ts3snet.unicbot_ms_spring.module_teamspeak.model.MessageType;
import local.ts3snet.unicbot_ms_spring.module_teamspeak.model.uniccore_messages.TeamspeakMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_teamspeak.service.impl.utils.TeamspeakMessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Lazy
@Component(value = "ts3sMessageSub")
public class Sub extends TeamspeakMessageAbstract {
    /**
     * Subscribe on bot
     *
     * @param bot bot sender service
     * @param msg user messages
     */
    @Override
    public void execute(TeamspeakMessageSender bot, String... msg) {
        TeamspeakUserEntity user = getTeamspeakUserService().findByTeamspeakToken(this.getUserToken());
        if(user == null) {
            user = new TeamspeakUserEntity();
            user.setTeamspeakToken(this.getUserToken());
            user.setSubscriber(false);
        }

        user.setSubscriber(! user.getSubscriber());

        getTeamspeakUserService().save(user);
        // sendPrivateMessage to client
        this.setTextMessage("-> sub on channel: " + user.getSubscriber());
        bot.sendPrivateMessage(this.getUserId(), this);

        printDebugLog();
    }

    @Override
    public String messageType() {
        return MessageType.SUBSCRIBER.getTextValue();
    }
}
