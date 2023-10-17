package seedu.address.model.event;

import seedu.address.model.person.Name;

import java.util.Optional;
import java.util.Set;

/**
 * Represents a Meeting in the address book.
 */
public class Meeting extends Event {

    private static final String EVENT_TYPE = "meeting";

    /**
     * Constructor for the meeting with optional start and end time
     * @param name name of the meeting
     * @param date date of the meeting
     * @param startTime start time of the meeting
     * @param endTime end time of the meeting
     */

    public Meeting(EventName name, EventDate date, Optional<EventTime> startTime, Optional<EventTime> endTime) {
        super(new EventType("meeting"), name, date, startTime, date, endTime);
    }

    public Meeting(EventName name, EventDate date,
                   Optional<EventTime> startTime, Optional<EventTime> endTime, Set<Name> names) {
        super(new EventType("meeting"), name, date, startTime, date, endTime, names);
    }



    /**
     * ToString for the meeting
     */
    @Override
    public String toString() {
        return "Meeting: " + super.getStartDate().toString();
    }

    /**
     * Checks if the meeting is the same as another meeting
     * @param other the other meeting to be compared to
     * @return true if the meetings have the same name.
     *
     */
    @Override
    public boolean isSameEvent(Event other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Meeting)) {
            return false;
        }

        return other.getName().equals(getName());
    }


}
