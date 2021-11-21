package local.ts3snet.unicbot_ms_spring.module_teamspeak.model;

public enum MessageType {
    DEFAULT("default"),
    SUBSCRIBER ("!sub"),
    STATION ("!station"),
    TRACK ("!track"),
    NEXT ("!next"),
    PREV("!prev"),
    GOTO("!goto");

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
