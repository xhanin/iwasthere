package iwasthere.domain;

import com.fasterxml.jackson.annotation.JsonView;
import org.joda.time.DateTime;
import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;
import restx.jackson.Views.Transient;

/**
 * Date: 12/4/14
 * Time: 13:21
 */
public class Event {

    @Id @ObjectId
    private String key;
    private String name;
    private DateTime date;

    @JsonView(Transient.class)
    private boolean iwasthere;

    @JsonView(Transient.class)
    private long count;

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public DateTime getDate() {
        return date;
    }

    public boolean isIwasthere() {
        return iwasthere;
    }

    public long getCount() {
        return count;
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

    public Event setIwasthere(final boolean iwasthere) {
        this.iwasthere = iwasthere;
        return this;
    }

    public Event setCount(final long count) {
        this.count = count;
        return this;
    }

    @Override
    public String toString() {
        return "Event{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", iwasthere=" + iwasthere +
                ", count=" + count +
                '}';
    }
}
