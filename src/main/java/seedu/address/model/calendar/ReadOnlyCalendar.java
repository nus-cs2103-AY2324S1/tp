package seedu.address.model.calendar;

import javafx.collections.ObservableList;
import seedu.address.model.event.Event;

/**
 * Unmodifiable view of the Calendar.
 */
public interface ReadOnlyCalendar {
    /**
     * Generates an unmodifiable view of the event list.
     *
     * @return unmodifiable view of event list.
     */
    ObservableList<Event> getEventList();

    /**
     * Generates an unmodifiable view of the event list for the current week.
     *
     * @return unmodifiable view of event list for the current week.
     */
    ObservableList<Event> getCurrentWeekEventList();
}
