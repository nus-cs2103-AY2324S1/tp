package seedu.address.model.event;

import java.time.LocalDateTime;
import java.util.ArrayList;

import seedu.address.model.person.Person;

/**
 * Represents an Event in the address book.
 */
public abstract class Event {

    private ArrayList<Person> persons = new ArrayList<>();

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    private Name name;

    /**
     * Constructor for the event with start datetime and end datetime
     * @param startDateTime start date and time of the event
     * @param endDateTime end date and time of the event
     */
    public Event(Name name, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.name = name;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Gets the start date time of the event
     * @return start date time of the event
     */
    public LocalDateTime getStartDateTime() {
        return this.startDateTime;
    }

    /**
     * Gets the end date time of the event
     * @return  end date time of the event
     */
    public LocalDateTime getEndDateTime() {
        return this.endDateTime;
    }

    /**
     * Gets the name of the event
     * @return name of the event
     */
    public Name getName() {
        return this.name;
    }
}
