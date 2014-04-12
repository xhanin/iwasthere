package iwasthere.domain;

import org.joda.time.DateTime;
import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

/**
 * Date: 12/4/14
 * Time: 13:21
 */
public class Event {

    @Id @ObjectId
    private String key;
    private String name;
    private DateTime date;

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public DateTime getDate() {
        return date;
    }

    public Event setKey(final String key) {
        this.key = key;
        return this;
    }

    public Event setName(final String name) {
        this.name = name;
        return this;
    }

    public Event setDate(final DateTime date) {
        this.date = date;
        return this;
    }

    @Override
    public String toString() {
        return "Event{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }

}
