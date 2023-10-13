package seedu.address.model.event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
    public Meeting(Name name, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(name, LocalDateTime.of(date, startTime), LocalDateTime.of(date, endTime));
    }

    /**
     * Constructor with just the date (without start and end time)
     * @param date date of the meeting
     */
    public Meeting(Name name, LocalDate date) {
        super(name, LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 0, 0),
                LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 0, 0));
    }

    /**
     * ToString for the meeting
     */
    @Override
    public String toString() {
        return "Meeting: " + super.getStartDateTime().toString();
    }
}
