package seedu.address.testutil;

import seedu.address.model.calendar.Calendar;
import seedu.address.model.event.Event;

/**
 * A utility class to help with building Calendar objects.
 * Example usage: <br>
 *     {@code Calendar c = new CalendarBuilder().withEvent("Description", "2000-01-01 00:00", "2000-01-01 12:00")
 *     .build();}
 */
public class CalendarBuilder {

    private Calendar calendar;

    public CalendarBuilder() {
        calendar = new Calendar();
    }

    public CalendarBuilder(Calendar calendar) {
        this.calendar = calendar;
    }

    /**
     * Adds a new {@code Person} to the {@code Calendar} that we are building.
     */
    public CalendarBuilder withEvent(Event event) {
        calendar.addEvent(event);
        return this;
    }

    public Calendar build() {
        return calendar;
    }
}
