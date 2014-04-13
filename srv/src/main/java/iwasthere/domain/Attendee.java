package iwasthere.domain;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 12/4/14
 * Time: 18:42
 */
public class Attendee {
    @Id @ObjectId
    private String key;

    private String fullName;
    private String emailHash;
    private String img;
    private String eventRef;

    private List<Message> messages = new ArrayList<>();

    public String getKey() {
        return key;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmailHash() {
        return emailHash;
    }

    public String getEventRef() {
        return eventRef;
    }

    public String getImg() {
        return img;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public Attendee setKey(final String key) {
        this.key = key;
        return this;
    }

    public Attendee setFullName(final String fullName) {
        this.fullName = fullName;
        return this;
    }


    public Attendee setEmailHash(final String emailHash) {
        this.emailHash = emailHash;
        return this;
    }

    public Attendee setImg(final String img) {
        this.img = img;
        return this;
    }

    public Attendee setEventRef(final String eventRef) {
        this.eventRef = eventRef;
        return this;
    }

    public Attendee setMessages(final List<Message> messages) {
        this.messages = messages;
        return this;
    }

    @Override
    public String toString() {
        return "Attendee{" +
                "key='" + key + '\'' +
                ", fullName='" + fullName + '\'' +
                ", emailHash='" + emailHash + '\'' +
                ", img='" + img + '\'' +
                ", eventRef='" + eventRef + '\'' +
                ", messages=" + messages +
                '}';
    }
}
