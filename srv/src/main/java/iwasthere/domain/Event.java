package iwasthere.domain;

import org.joda.time.DateTime;

/**
 * Date: 12/4/14
 * Time: 13:21
 */
public class Event {
    private String name;
    private DateTime date;

    public String getName() {
        return name;
    }

    public DateTime getDate() {
        return date;
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
                "name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
