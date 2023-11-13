package seedu.address.model.calendar;

import java.time.LocalTime;
import java.util.Optional;

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

    /**
     * Retrieves the earliest starting time of any event for the current week in a LocalTime object.
     *
     * @return the earliest starting time of any event for the current week in a LocalTime object.
     */
    Optional<LocalTime> getEarliestEventStartTimeInCurrentWeek();

    /**
     * Retrieves the latest ending time of any event for the current week in a LocalTime object.
     *
     * @return the latest ending time of any event for the current week in a LocalTime object.
     */
    Optional<LocalTime> getLatestEventEndTimeInCurrentWeek();

    /**
     * Combine this calendar with another calendar, disregarding conflicts in events.
     *
     * @param other other calendar to be combined.
     * @return new calendar with events from both calendars.
     */
    ReadOnlyCalendar getCombinedCalendar(ReadOnlyCalendar other);
}
