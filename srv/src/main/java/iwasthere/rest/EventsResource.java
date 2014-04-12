package iwasthere.rest;

import iwasthere.domain.Event;
import org.joda.time.DateTime;
import restx.annotations.GET;
import restx.annotations.RestxResource;
import restx.factory.Component;

import static java.util.Arrays.asList;

/**
 * Date: 12/4/14
 * Time: 13:20
 */
@Component
@RestxResource
public class EventsResource {

    @GET("/events")
    public Iterable<Event> findEvents() {
        return asList(
                new Event().setName("RESTX @ DevoxxFR 2014").setDate(DateTime.parse("2014-04-18T18:00:00+02:00")),
                new Event().setName("Meet and Greet @ DevoxxFR 2014").setDate(DateTime.parse("2014-04-18T19:30:00+02:00"))
        );
    }
}
