package seedu.address.model.util;

import seedu.address.model.ReadOnlyCalendar;
import seedu.address.model.calendar.Calendar;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDescription;
import seedu.address.model.event.EventPeriod;

/**
 * Contains utility methods for populating {@code Calendar} with sample data.
 */
public class SampleCalendarUtil {
    public static Event[] getSampleEvents() {
        return new Event[] {
                new Event(new EventDescription("Weekly team meeting"),
                        new EventPeriod("2023-10-25 09:00", "2023-10-25 10:30")),
                new Event(new EventDescription(" Annual health checkup"),
                        new EventPeriod("2023-11-10 15:15", "2023-11-10 16:00")),
                new Event(new EventDescription(" Birthday celebration"),
                        new EventPeriod("2023-11-15 18:00", "2023-11-15 22:00"))
        };
    }

    public static ReadOnlyCalendar getSampleCalendar() {
        Calendar sampleCalendar = new Calendar();
        for (Event sampleEvent : getSampleEvents()) {
            sampleCalendar.addEvent(sampleEvent);
        }
        return sampleCalendar;
    }

}
