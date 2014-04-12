package iwasthere.rest;

import iwasthere.domain.Event;
import restx.annotations.GET;
import restx.annotations.POST;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.jongo.JongoCollection;

import javax.inject.Named;

/**
 * Date: 12/4/14
 * Time: 13:20
 */
@Component
@RestxResource
public class EventsResource {

    private final JongoCollection events;

    public EventsResource(@Named("events") JongoCollection events) {
        this.events = events;
    }

    @GET("/events")
    public Iterable<Event> findEvents() {
        return events.get().find().as(Event.class);
    }

    @POST("/events")
    public Event addEvent(Event event) {
        events.get().save(event);
        return event;
    }
}
