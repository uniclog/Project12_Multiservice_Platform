package local.ts3snet.unicbot_ms_spring.module_teamspeak.model.uniccore_messages.impl;

import local.ts3snet.unicbot_ms_spring.module_teamspeak.model.MessageType;
import local.ts3snet.unicbot_ms_spring.module_teamspeak.model.uniccore_messages.TeamspeakMessageAbstract;
import local.ts3snet.unicbot_ms_spring.module_teamspeak.service.impl.utils.TeamspeakMessageSender;
import local.uniclog.frame_dataaccess.entity.TeamspeakUserEntity;
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
        TeamspeakUserEntity user = this.getTeamspeakUserService().findByTeamspeakToken(this.getUserToken());
        if(user == null) {
            user = new TeamspeakUserEntity();
            user.setTeamspeakToken(this.getUserToken());
            user.setSubscriber(true);
        }
        user.setSubscriber(!user.getSubscriber());
        this.getTeamspeakUserService().save(user);
        // sendPrivateMessage to client
        bot.sendPrivateMessage(this.getUserId(), "-> sub on channel: " + user.getSubscriber());

        printDebugLog();
    }

    @Override
    public String messageType() {
        return MessageType.SUBSCRIBER.getTextValue();
    }
}
