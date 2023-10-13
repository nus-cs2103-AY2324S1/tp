package seedu.address.model.event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import seedu.address.model.person.Person;

/**
 * Represents an Event in the address book.
 */
public abstract class Event {

    private ArrayList<Person> persons = new ArrayList<>();

    private EventDate startDate;

    private EventTime startTime;
    private EventDate endDate;

    private EventTime endTime;

    private EventName name;

    public Event(EventName name, EventDate startDate, EventTime startTime, EventDate endDate, EventTime endTime) {
        this.name = name;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;

    }

    /**
     * Gets the start date time of the event
     * @return start date time of the event
     */
    public EventDate getStartDate() {
        return this.startDate;
    }

    public EventTime getStartTime() {
        return this.startTime;
    }

    public EventDate getEndDate() {
        return this.endDate;
    }

    public EventTime getEndTime() {
        return this.endTime;
    }

    /**
     * Gets the name of the event
     * @return name of the event
     */
    public EventName getName() {
        return this.name;
    }
}
