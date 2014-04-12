package iwasthere.rest;

import com.google.common.base.Charsets;
import com.google.common.base.Optional;
import com.google.common.hash.Hashing;
import iwasthere.AppModule.Roles;
import iwasthere.domain.Attendee;
import iwasthere.domain.Event;
import org.bson.types.ObjectId;
import restx.WebException;
import restx.annotations.GET;
import restx.annotations.POST;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.http.HttpStatus;
import restx.jongo.JongoCollection;
import restx.security.PermitAll;
import restx.security.RestxSession;

import javax.inject.Named;

/**
 * Date: 12/4/14
 * Time: 13:20
 */
@Component
@RestxResource
public class EventsResource {

    private final JongoCollection events;
    private final JongoCollection attendees;

    public EventsResource(@Named("events") JongoCollection events,
                          @Named("attendees") JongoCollection attendees) {
        this.events = events;
        this.attendees = attendees;
    }

    @PermitAll
    @GET("/events")
    public Iterable<Event> findEvents() {
        return events.get().find().as(Event.class);
    }

    @PermitAll
    @GET("/events/:key")
    public Optional<Event> findEvent(String key) {
        return Optional.fromNullable(events.get().findOne(new ObjectId(key)).as(Event.class));
    }

    @PermitAll
    @POST("/events")
    public Event addEvent(Event event) {
        events.get().save(event);
        return event;
    }

    @PermitAll
    @GET("/events/:eventKey/attendees")
    public Iterable<Attendee> getEventAttendees(String eventKey) {
        return attendees.get().find("{eventRef: # }", eventKey).as(Attendee.class);
    }

    @POST("/events/:eventKey/attendees")
    public Attendee addAttendee(String eventKey, Attendee attendee) {
        if (!Hashing.md5().hashString(RestxSession.current().getPrincipal().get().getName(), Charsets.UTF_8).toString()
                .equals(attendee.getEmailHash())
                && !RestxSession.current().getPrincipal().get().getPrincipalRoles().contains(Roles.ADMIN)) {
            // non admin users can't add attendee other than self
            throw new WebException(HttpStatus.FORBIDDEN);
        }

        attendee.setEventRef(eventKey);

        attendees.get().save(attendee);
        return attendee;
    }
}
