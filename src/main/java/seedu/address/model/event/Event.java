package seedu.address.model.event;

import seedu.address.model.person.Person;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents an Event in the address book.
 */
public abstract class Event {

    ArrayList<Person> person;
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;

    /**
     * Constructor for the event with start datetime and end datetime
     * @param startDateTime start date and time of the event
     * @param endDateTime end date and time of the event
     */
    public Event(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.person = new ArrayList<>();
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public LocalDate getDateTime() {
        return this.startDateTime.toLocalDate();
    }
}
