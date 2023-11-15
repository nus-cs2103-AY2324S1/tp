package seedu.address.testutil;

import java.util.ArrayList;

import seedu.address.model.person.timetable.DatedEvent;

/**
 * A utility class to help with building DatedEvents objects.
 * Example usage: <br>
 *     {@code DatedEvents de = new DatedEventsBuilder().withDatedEvent("CS2103T Lecture").build();}
 */
public class DatedEventsBuilder {
    private ArrayList<DatedEvent> datedEvents;

    /**
     * Creates a {@code DatedEventsBuilder} with the default details.
     */
    public DatedEventsBuilder(ArrayList<DatedEvent> datedEvents) {
        this.datedEvents = datedEvents;
    }

    /**
     * Creates a {@code DatedEventsBuilder} with the default details.
     */
    public DatedEventsBuilder() {
        datedEvents = new ArrayList<>();
    }

    /**
     * Adds a new {@code DatedEvent} to the {@code DatedEvents} that we are building.
     */
    public DatedEventsBuilder withDatedEvent(DatedEvent datedEvent) {
        datedEvents.add(datedEvent);
        return this;
    }

    /**
     * Builds the DatedEvents object.
     * @return DatedEvents object.
     */
    public ArrayList<DatedEvent> build() {
        return datedEvents;
    }

}
