package seedu.address.model.util;

import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.parser.DateTimeParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Duration;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDescription;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Location;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {
    public static final String DEFAULT_NAME = "Meeting with Alice";
    public static final String DEFAULT_TIME_START = "next year 09:00";
    public static final String DEFAULT_TIME_END = "next year 10:00";
    public static final String DEFAULT_EVENT_DESCRIPTION = "Discussion";
    public static final String DEFAULT_LOCATION = "Conference Room";

    private EventName eventName;
    private Duration duration;
    private EventDescription eventDescription;
    private Location location;
    private Set<Person> clients = new HashSet<>();

    /**
     * Creates an {@code EventBuilder} with the default details.
     */
    public EventBuilder() throws ParseException {
        eventName = new EventName(DEFAULT_NAME);
        duration = DateTimeParser.parseDateTimeDuration(DEFAULT_TIME_START, DEFAULT_TIME_END);
        eventDescription = new EventDescription(DEFAULT_EVENT_DESCRIPTION);
        location = new Location(DEFAULT_LOCATION);
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        eventName = eventToCopy.getEventName();
        duration = eventToCopy.getDuration();
        eventDescription = eventToCopy.getDescription();
        location = eventToCopy.getLocation();
        clients = eventToCopy.getClients().isEmpty() ? new HashSet<>() : eventToCopy.getClients();
    }

    /**
     * Sets the {@code Name} of the {@code Event} that we are building.
     */
    public EventBuilder withName(String name) {
        this.eventName = new EventName(name);
        return this;
    }

    /**
     * Sets the {@code Duration} of the {@code Event} that we are building.
     * @throws ParseException if input is invalid.
     */
    public EventBuilder withDuration(String timeStart, String timeEnd) throws ParseException {
        this.duration = DateTimeParser.parseDateTimeDuration(timeStart, timeEnd);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Event} that we are building.
     */
    public EventBuilder withEventDescription(String description) {
        this.eventDescription = new EventDescription(description);
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Event} that we are building.
     */
    public EventBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    /**
     * Sets the {@code Person} associated with the {@code Event} that we are building.
     */
    public EventBuilder withClient(Person person) {
        this.clients.add(person);
        return this;
    }

    /**
     * Builds an event with the given attributes.
     */
    public Event build() {
        return new Event(eventName, duration, clients, location, eventDescription);
    }
}
