package iwasthere.domain;

import org.joda.time.DateTime;

public class Message {
    private DateTime timestamp;
    private String text;
    private String mood;

    public DateTime getTimestamp() {
        return timestamp;
    }

    public String getText() {
        return text;
    }

    public String getMood() {
        return mood;
    }

    public Message setTimestamp(final DateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Message setText(final String text) {
        this.text = text;
        return this;
    }

    public Message setMood(final String mood) {
        this.mood = mood;
        return this;
    }

    @Override
    public String toString() {
        return "Message{" +
                "timestamp=" + timestamp +
                ", text='" + text + '\'' +
                ", mood='" + mood + '\'' +
                '}';
    }
}
