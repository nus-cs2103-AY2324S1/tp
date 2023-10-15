package seedu.address.model.event;

import java.util.ArrayList;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Represents an Event in the address book.
 */
public abstract class Event {

    private final ArrayList<Person> persons = new ArrayList<>();

    private final EventDate startDate;

    private final EventTime startTime;
    private final EventDate endDate;

    private final EventTime endTime;

    private final EventName name;

    private final EventType eventType;

    /**
     * Constructor for the event with optional start and end time
     * @param eventType type of the event
     * @param name name of the event
     * @param startDate start date of the event
     * @param startTime start time of the event
     * @param endDate end date of the event
     * @param endTime end time of the event
     */
    public Event(EventType eventType, EventName name, EventDate startDate, EventTime startTime,
                 EventDate endDate, EventTime endTime) {
        this.eventType = eventType;
        this.name = name;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    public EventType getEventType() {
        return this.eventType;
    }

    /**
     * Gets the start date time of the event
     * @return start date time of the event
     */
    public EventDate getStartDate() {
        return this.startDate;
    }

    /**
     * Get the start time of the event
     * @return start time of the event
     */
    public EventTime getStartTime() {
        return this.startTime;
    }

    /**
     * Gets the end date of the event
     * @return end date of the event
     */
    public EventDate getEndDate() {
        return this.endDate;
    }

    /**
     * Gets the end time of the event
     * @return end time of the event
     */
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("meeting_details", this.name)
                .add("date", this.startDate)
                .add("start_time", this.startTime)
                .add("end_time", this.endTime)
                .toString();
    }
}
