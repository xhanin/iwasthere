package iwasthere.domain;

/**
 * Date: 12/4/14
 * Time: 18:42
 */
public class Attendee {
    private String fullName;
    private String emailHash;
    private String img;
    private String eventRef;

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

    @Override
    public String toString() {
        return "Attendee{" +
                "fullName='" + fullName + '\'' +
                ", emailHash='" + emailHash + '\'' +
                ", img='" + img + '\'' +
                ", eventRef='" + eventRef + '\'' +
                '}';
    }
}
