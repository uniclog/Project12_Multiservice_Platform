package local.ts3snet.unicbot_ms_spring.module_telegram.model.myfitness_messages;

public enum MessageType {
    DEFAULT("default"),
    SUB ("/sub"),
    UNSUB ("/unsub"),
    HELP ("/help"),
    STATISTIC_CLEAR("/statistic_clear"),
    INLINE_BUTTON_ACCEPT("/inline_button_accept"),
    INLINE_BUTTON_ACCEPT_SOURCE("/inline_button_accept_source"),
    MESSAGE_FOR_ALL_SUBSCRIBERS ("message_for_all_subscribers");

    private final String value;
    MessageType(String value) {
        this.value = value;
    }
    public static MessageType getType(String msg) {
        for (MessageType e : MessageType.values()) {
            if (e.value.equalsIgnoreCase(msg))
                return e;
        }
        return DEFAULT;
    }
    public String getTextValue() {
        return this.value;
    }
}
