package iwasthere.rest;

import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.Iterables;
import com.google.common.hash.Hashing;
import iwasthere.AppModule.Roles;
import iwasthere.domain.Attendee;
import iwasthere.domain.Event;
import iwasthere.domain.Message;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import restx.WebException;
import restx.annotations.GET;
import restx.annotations.POST;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.http.HttpStatus;
import restx.jongo.JongoCollection;
import restx.security.PermitAll;
import restx.security.RestxPrincipal;
import restx.security.RestxSession;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collection;


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

    /**
     * Find all events covered by iwasthere.
     *
     * @return the list of events
     */
    @PermitAll
    @GET("/events")
    public Iterable<Event> findEvents() {
        return Iterables.transform(this.events.get().find().as(Event.class), new Function<Event, Event>() {
            @Override
            public Event apply(Event input) {
                return loadEventTransientFields(input);
            }
        });
    }

    /**
     * Find a particular event by key
     * @param key event key
     * @return the event
     */
    @PermitAll
    @GET("/events/:key")
    public Optional<Event> findEvent(String key) {
        Event event = events.get().findOne(new ObjectId(key)).as(Event.class);
        if (event == null) {
            return Optional.absent();
        }
        return Optional.of(loadEventTransientFields(event));
    }

    private Event loadEventTransientFields(Event event) {
        return event
                .setCount(attendees.get().count("{eventRef: #}", event.getKey()))
                .setIwasthere(wasSelfThere(event.getKey()))
        ;
    }

    private boolean wasSelfThere(String eventKey) {
        Optional<? extends RestxPrincipal> principal = RestxSession.current().getPrincipal();
        if (!principal.isPresent()) {
            return false;
        }
        return attendees.get().count("{eventRef: #, emailHash: #}", eventKey, selfEmailHash()) > 0;
    }

    /**
     * Creates a new event covered by iwasthere.
     *
     * @param event the event to add.
     * @return the created event.
     */
    @PermitAll
    @POST("/events")
    public Event addEvent(Event event) {
        events.get().save(event);
        return event;
    }

    /**
     * Returns all the attendees of an event.
     *
     * @param eventKey the key of the event for which attendees should be returned.
     * @return the event attendees.
     */
    @PermitAll
    @GET("/events/:eventKey/attendees")
    public Iterable<Attendee> getEventAttendees(String eventKey) {
        return attendees.get().find("{eventRef: # }", eventKey).as(Attendee.class);
    }

    @POST("/events/:eventKey/attendees")
    public Attendee addAttendee(String eventKey, Attendee attendee) {
        checkSelfOrAdmin(attendee.getEmailHash());

        attendee.setEventRef(eventKey);

        attendees.get().save(attendee);
        return attendee;
    }

    @POST("/events/:eventKey/attendees/:attendeeEmailHash/messages")
    public Message addMessage(String eventKey, String attendeeEmailHash,  Message message) {
        checkSelfOrAdmin(attendeeEmailHash);

        Attendee attendee = attendees.get()
                .findOne("{eventRef: #, emailHash: # }", eventKey, attendeeEmailHash).as(Attendee.class);

        if (attendee == null) {
            throw new WebException(HttpStatus.NOT_FOUND);
        }

        attendee.getMessages().add(message.setTimestamp(DateTime.now()));
        attendees.get().save(attendee);

        return message;
    }

    private void checkSelfOrAdmin(String attendeeEmailHash) {
        if (!selfEmailHash()
                .equals(attendeeEmailHash)
                && !RestxSession.current().getPrincipal().get().getPrincipalRoles().contains(Roles.ADMIN)) {
            // non admin users can't add messages to attendee other than self
            throw new WebException(HttpStatus.FORBIDDEN);
        }
    }

    private String selfEmailHash() {
        return Hashing.md5().hashString(RestxSession.current().getPrincipal().get().getName(), Charsets.UTF_8).toString();
    }
}
