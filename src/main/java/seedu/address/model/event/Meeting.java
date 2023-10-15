package seedu.address.model.event;

/**
 * Represents a Meeting in the address book.
 */
public class Meeting extends Event {

    private static final String EVENT_TYPE = "meeting";

    /**
     * Constructor for the meeting with optional start and end time
     * @param date date of the meeting
     * @param startTime start time of the meeting
     * @param endTime end time of the meeting
     */
    public Meeting(EventType eventType, EventName name, EventDate date, EventTime startTime, EventTime endTime) {
        super(eventType, name, date, startTime, date, endTime);
    }

    @Override
    public String toString() {
        return String.format("%s; Date: %s; Start_Time: %s; End_Time: %s",
                this.getName(), this.getStartDate(), this.getStartTime(), this.getEndTime());
    }
}
