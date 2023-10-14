package seedu.address.model.event;

/**
 * Represents a Meeting in the address book.
 */
public class Meeting extends Event {

    /**
     * Constructor for the meeting with optional start and end time
     * @param date date of the meeting
     * @param startTime start time of the meeting
     * @param endTime end time of the meeting
     */
    public Meeting(EventName name, EventDate date, EventTime startTime, EventTime endTime) {
        super(name, date, startTime, date, endTime);
    }

    /**
     * Constructor with just the date (without start and end time)
     * @param date date of the meeting
     */
    public Meeting(EventName name, EventDate date) {
        super(name, date, null, date, null);
    }

    /**
     * ToString for the meeting
     */
    @Override
    public String toString() {
        return "Meeting: " + super.getName();
    }
}
