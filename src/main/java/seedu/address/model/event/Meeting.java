package seedu.address.model.event;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a Meeting in the address book.
 */
public class Meeting extends Event {

    LocalDateTime startTime;
    LocalDateTime endTime;

    /**
     * Constructor for the meeting with optional start and end time
     * @param date date of the meeting
     * @param startTime start time of the meeting
     * @param endTime end time of the meeting
     */
    public Meeting(LocalDate date, LocalDateTime startTime, LocalDateTime endTime) {
        super(date);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructor for the meeting with only date (no start and end time)
     * @param date date of the meeting
     */
    public Meeting(LocalDate date) {
        super(date);
    }

    /**
     * ToString for the meeting
     */
    @Override
    public String toString() {
        return "Meeting: " + date.toString();
    }

    /**
     * Assign a person to meeting
     */

}
