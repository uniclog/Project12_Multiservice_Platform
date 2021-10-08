package local.ts3snet.unicbot_ms_spring.module_teamspeak.model;

public enum MessageType {
    DEFAULT {
        @Override
        public String toString() {
            return "default";
        }
    },
    MESSAGE_FOR_ALL_SUBSCRIBERS {
        @Override
        public String toString() {
            return "messageForAllSubscribers";
        }
    },
    SUB {
        @Override
        public String toString() {
            return "/sub";
        }
    },
    UNSUB {
        @Override
        public String toString() {
            return "/unsub";
        }
    },
    TRACK {
        @Override
        public String toString() {
            return "/track";
        }
    },
    STATION {
        @Override
        public String toString() {
            return "/station";
        }
    },
    NEXT {
        @Override
        public String toString() {
            return "/next";
        }
    },
    PREV {
        @Override
        public String toString() {
            return "/prev";
        }
    },
    PLAY_BY_ID {
        @Override
        public String toString() {
            return "/goto";
        }
    }
}
