package seedu.address.testutil;

import seedu.address.model.event.Event;
import seedu.address.model.event.EventDescription;
import seedu.address.model.event.EventPeriod;

/**
 * A utility class to help with building event objects.
 */
public class EventBuilder {
    public static final String DEFAULT_DESCRIPTION_STRING = "Unused field";
    public static final String DEFAULT_START_TIME_STRING = "2023-01-01 13:00";
    public static final String DEFAULT_END_TIME_STRING = "2023-01-01 15:00";

    private EventDescription description;
    private EventPeriod eventPeriod;

    /**
     * Creates an EventBuilder with default description and event periods.
     */
    public EventBuilder() {
        this.description = new EventDescription(DEFAULT_DESCRIPTION_STRING);
        this.eventPeriod = new EventPeriod(DEFAULT_START_TIME_STRING, DEFAULT_END_TIME_STRING);
    }

    /**
     * Creates an event with the given EventBuilder attributes.
     *
     * @return event corresponding to EventBuilder's attributes.
     */
    public Event build() {
        return new Event(this.description, this.eventPeriod);
    }

    /**
     * Set the description to the user input.
     *
     * @param description user input.
     * @return EventBuilder object with description changed to user input.
     */
    public EventBuilder withDescription(String description) {
        this.description = new EventDescription(description);
        return this;
    }

    /**
     * Set the start date to the user input while setting the end date to the default.
     *
     * @param startDate user input in format 'yyyy-MM-dd HH:mm'.
     * @return EventBuilder object with start date changed to user input.
     */
    public EventBuilder withStartDate(String startDate) {
        this.eventPeriod = new EventPeriod(startDate, DEFAULT_END_TIME_STRING);
        return this;
    }

    /**
     * Set the end date to the user input while setting the start date to the default.
     *
     * @param endDate user input in format 'yyyy-MM-dd HH:mm'.
     * @return EventBuilder object with the end date changed to user input.
     */
    public EventBuilder withEndDate(String endDate) {
        this.eventPeriod = new EventPeriod(DEFAULT_START_TIME_STRING, endDate);
        return this;
    }

    /**
     * Set the start and end date to the user input.
     *
     * @param start user input start date in format 'yyyy-MM-dd HH:mm'.
     * @param end user input end date in format 'yyyy-MM-dd HH:mm'.
     * @return EventBuilder object with the start and end date changed to respective user inputs.
     */
    public EventBuilder withStartEndDate(String start, String end) {
        this.eventPeriod = new EventPeriod(start, end);
        return this;
    }
}
