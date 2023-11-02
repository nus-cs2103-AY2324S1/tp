package seedu.address.testutil;

import seedu.address.model.calendar.UniMateCalendar;
import seedu.address.model.event.Event;

/**
 * A utility class to help with building Calendar objects.
 * Example usage: <br>
 *     {@code Calendar c = new CalendarBuilder().withEvent("Description", "2000-01-01 00:00", "2000-01-01 12:00")
 *     .build();}
 */
public class CalendarBuilder {

    private UniMateCalendar calendar;

    public CalendarBuilder() {
        calendar = new UniMateCalendar();
    }

    public CalendarBuilder(UniMateCalendar calendar) {
        this.calendar = calendar;
    }

    /**
     * Adds a new {@code Event} to the {@code Calendar} that we are building.
     */
    public CalendarBuilder withEvent(Event event) {
        calendar.addEvent(event);
        return this;
    }

    public UniMateCalendar build() {
        return calendar;
    }
}
