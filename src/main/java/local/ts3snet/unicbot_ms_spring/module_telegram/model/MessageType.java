package local.ts3snet.unicbot_ms_spring.module_telegram.model;

public class MessageType {
    public static final String DEFAULT = "default";
    public static final String MESSAGE_FOR_ALL_SUBSCRIBERS = "messageForAllSubscribers";
    public static final String SUB = "/sub";
    public static final String UNSUB = "/unsub";
    public static final String TRACK = "/track";
    public static final String STATION = "/station";
    public static final String NEXT = "/next";
    public static final String PREV = "/prev";
    public static final String PLAY_BY_ID = "/goto";

    private MessageType() {}
}
