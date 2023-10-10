package seedu.address.testutil;

import seedu.address.model.event.EventPeriod;

/**
 * Creates an EventPeriodBuilder with default event period.
 */
public class EventPeriodBuilder {
    private static final String DEFAULT_START_DATE_TIME_STRING = "2023-01-02 13:00";
    private static final String DEFAULT_END_DATE_TIME_STRING = "2023-01-02 15:00";
    private EventPeriod eventPeriod;

    /**
     * Creates an EventPeriodBuilder object with default event period.
     */
    public EventPeriodBuilder() {
        this.eventPeriod = new EventPeriod(DEFAULT_START_DATE_TIME_STRING, DEFAULT_END_DATE_TIME_STRING);
    }

    /**
     * Creates an EventPeriod with given EventPeriodBuilder attributes.
     *
     * @return EventPeriod corresponding to the EventPeriodBuilder attributes.
     */
    public EventPeriod build() {
        return this.eventPeriod;
    }

    /**
     * Change the start date and time to the user input while setting the end date and time to default.
     *
     * @param newStart user input in format 'yyyy-MM-dd HH:mm'.
     * @return EventPeriodBuilder object with start date and time set to user input.
     */
    public EventPeriodBuilder changeStart(String newStart) {
        this.eventPeriod = new EventPeriod(newStart, DEFAULT_END_DATE_TIME_STRING);
        return this;
    }

    /**
     * Change the end date and time to the user input while setting the start date and time to default.
     *
     * @param newEnd user input in format 'yyyy-MM-dd HH:mm'.
     * @return EventPeriodBuilder object with end date and time set to user input.
     */
    public EventPeriodBuilder changeEnd(String newEnd) {
        this.eventPeriod = new EventPeriod(DEFAULT_START_DATE_TIME_STRING, newEnd);
        return this;
    }

    /**
     * Change the start date and time and end date and time to the user input.
     *
     * @param newStart start date and time user input in format 'yyyy-MM-dd HH:mm'
     * @param newEnd end date and time user input in format 'yyyy-MM-dd HH:mm'
     * @return EventPeriodBuilder object with start and end date and time set to user input.
     */
    public EventPeriodBuilder changeStartAndEnd(String newStart, String newEnd) {
        this.eventPeriod = new EventPeriod(newStart, newEnd);
        return this;
    }
}
