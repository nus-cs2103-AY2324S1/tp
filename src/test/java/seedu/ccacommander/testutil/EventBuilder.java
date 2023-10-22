package seedu.ccacommander.testutil;

import seedu.ccacommander.model.event.Event;
import seedu.ccacommander.model.event.EventDate;
import seedu.ccacommander.model.event.Location;
import seedu.ccacommander.model.shared.Name;

/**
 * A utility class to help with building Events objects.
 */
public class EventBuilder {

    public static final String DEFAULT_EVENT_NAME = "NUS Party";
    public static final String DEFAULT_EVENT_DATE = "2023-12-08";
    public static final String DEFAULT_LOCATION = "NUS COM3";

    private Name name;
    private EventDate date;
    private Location location;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        name = new Name(DEFAULT_EVENT_NAME);
        date = new EventDate(DEFAULT_EVENT_DATE);
        location = new Location(DEFAULT_LOCATION);
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        name = eventToCopy.getName();
        date = eventToCopy.getDate();
        location = eventToCopy.getLocation();
    }

    /**
     * Sets the {@code Name} of the {@code Event} that we are building.
     */
    public EventBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Event} that we are building.
     */
    public EventBuilder withDate(String date) {
        this.date = new EventDate(date);
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Event} that we are building.
     */
    public EventBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }



    public Event build() {
        return new Event(name, date, location);
    }

}
