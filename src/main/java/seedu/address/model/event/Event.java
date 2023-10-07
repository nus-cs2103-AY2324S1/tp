package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents an event in the calendar
 */
public class Event {

    private EventDescription description;
    private EventPeriod eventPeriod;

    public Event(EventDescription description, EventPeriod eventPeriod) {
        requireAllNonNull(description, eventPeriod);

        this.description = description;
        this.eventPeriod = eventPeriod;
    }

    public static Event createNonConflictingEvent() {
        return new Event(EventDescription.createUnusedDescription(), EventPeriod.createNonConflictingPeriod());
    }

    public EventDescription getDescription() {
        return this.description;
    }

    public EventPeriod getEventPeriod() {
        return this.eventPeriod;
    }


    public boolean isConflicting(Event other) {
        requireNonNull(other);

        return this.eventPeriod.isOverlapping(other.eventPeriod);
    }

    public void changeDescription(EventDescription description) {
        requireNonNull(description);

        this.description = description;
    }

    public void changeEventPeriod(EventPeriod eventPeriod) {
        requireNonNull(eventPeriod);

        this.eventPeriod = eventPeriod;
    }
}
