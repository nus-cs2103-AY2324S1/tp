package seedu.address.model.calendar;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.event.AllDaysEventListManager;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventPeriod;

/**
 * Represents a calendar that stores and manages events.
 */
public class UniMateCalendar implements ReadOnlyCalendar {
    private static final int DAYS_IN_WEEK = 7;
    private static final LocalDate DATE_OF_START_OF_CURRENT_WEEK = LocalDate.now().minusDays(
            LocalDate.now().getDayOfWeek().getValue() - 1);
    private static final LocalDate DATE_OF_END_OF_CURRENT_WEEK = LocalDate.now().plusDays(
            DAYS_IN_WEEK - LocalDate.now().getDayOfWeek().getValue());
    private final AllDaysEventListManager eventManager;
    private final ObservableList<Event> internalList = FXCollections.observableArrayList();
    private final ObservableList<Event> internalListForCurrentWeek = FXCollections.observableArrayList();

    /**
     * Constructs a Calendar object with an empty event tree.
     */
    public UniMateCalendar() {
        this.eventManager = new AllDaysEventListManager();
    }

    /**
     * Creates a Calendar using the Events in the {@code toBeCopied}
     */
    public UniMateCalendar(ReadOnlyCalendar toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// overwrite operations

    /**
     * Replaces the contents of the Calendar with {@code events}.
     * {@code events} must not contain duplicate events.
     */
    public void setEvents(List<Event> events) {
        events.forEach(this::addEvent);
    }

    /**
     * Resets the existing data of this {@code Calendar} with {@code newData}.
     */
    public void resetData(ReadOnlyCalendar newData) {
        requireNonNull(newData);

        setEvents(newData.getEventList());
    }

    /**
     * Return the AllDaysEventListManager managing the events for this calendar.
     *
     * @return the AllDaysEventListManager managing the events.
     */
    public AllDaysEventListManager getEventManager() {
        return this.eventManager;
    }

    /**
     * Check if the event can be added to the calendar
     *
     * @param event event to be checked.
     * @return true if it can be added, false otherwise.
     */
    public boolean canAddEvent(Event event) {
        return eventManager.canAddEvent(event);
    }

    /**
     * Adds an event to the calendar.
     *
     * @param event The event to be added.
     */
    public void addEvent(Event event) {
        requireNonNull(event);
        eventManager.addEvent(event);
        internalList.setAll(eventManager.asUnmodifiableObservableList());
        internalListForCurrentWeek.setAll(eventManager.asUnmodifiableObservableList(
                DATE_OF_START_OF_CURRENT_WEEK, DATE_OF_END_OF_CURRENT_WEEK));
    }

    /**
     * Force add the event into the event manager.
     *
     * @param event event to be added.
     */
    private void forceAddEvent(Event event) {
        requireNonNull(event);
        eventManager.forceAddEvent(event);
        internalList.setAll(eventManager.asUnmodifiableObservableList());
        internalListForCurrentWeek.setAll(eventManager.asUnmodifiableObservableList(
                DATE_OF_START_OF_CURRENT_WEEK, DATE_OF_END_OF_CURRENT_WEEK));
    }

    /**
     * Checks if there is an event at specified time and deletes it if there is.
     * @param dateTime the specified time
     */
    public void deleteEventAt(LocalDateTime dateTime) {
        requireNonNull(dateTime);
        eventManager.deleteEventAt(dateTime);
        internalList.setAll(eventManager.asUnmodifiableObservableList());
        internalListForCurrentWeek.setAll(eventManager.asUnmodifiableObservableList(
                DATE_OF_START_OF_CURRENT_WEEK, DATE_OF_END_OF_CURRENT_WEEK));
    }

    /**
     * Looks for an event at specified time.
     *
     * @param dateTime the specified time.
     * @return an optional containing the event if there is an event at the time, an empty optional otherwise.
     */
    public Optional<Event> findEventAt(LocalDateTime dateTime) {
        requireNonNull(dateTime);
        return eventManager.eventAt(dateTime);
    }

    /**
     * Looks for all events within a specified time range and returns them in a list.
     *
     * @param range the specified time range represented by an {@code EventPeriod}
     * @return a list containing all events within the range.
     */
    public List<Event> getEventsInRange(EventPeriod range) {
        requireNonNull(range);
        return eventManager.eventsInRange(range);
    }

    /**
     * Looks for all events within a specified time range and deletes them.
     *
     * @param range the specified time range represented by an {@code EventPeriod}
     */
    public void deleteEventsInRange(EventPeriod range) {
        requireNonNull(range);
        for (Event event:getEventsInRange(range)) {
            deleteEventAt(event.getStartDateTime());
        }
    }

    /**
     * Clears the calendar
     */
    public void clear() {
        this.eventManager.clear();
    }

    /**
     * Check if the calendar contains the event.
     *
     * @param event event to be checked.
     * @return true if it is contained in the calendar, false otherwise.
     */
    public boolean contains(Event event) {
        return this.eventManager.contains(event);
    }

    /**
     * Checks if the calendar is empty.
     *
     * @return true if calendar is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.eventManager.isEmpty();
    }

    /**
     * Checks if there are any events at all in the calendar.
     *
     * @return true if there are events in the calendar, false otherwise.
     */
    public boolean hasEvents() {
        if (!this.isEmpty()) {
            return eventManager.hasEvents();
        }
        return false;
    }

    @Override
    public ReadOnlyCalendar getCombinedCalendar(ReadOnlyCalendar other) {
        requireNonNull(other);

        UniMateCalendar combinedCalendar = new UniMateCalendar();
        Stream.concat(internalList.stream(), other.getEventList().stream()).forEach(combinedCalendar::forceAddEvent);
        return combinedCalendar;
    }

    @Override
    public ObservableList<Event> getEventList() {
        return internalList;
    }

    @Override
    public ObservableList<Event> getCurrentWeekEventList() {
        return internalListForCurrentWeek;
    }

    @Override
    public Optional<LocalTime> getEarliestEventStartTimeInCurrentWeek() {
        return getCurrentWeekEventList().stream().min(Event::compareStartTime).map(Event::getStartTime);
    }

    @Override
    public Optional<LocalTime> getLatestEventEndTimeInCurrentWeek() {
        return getCurrentWeekEventList().stream().max(Event::compareEndTime).map(Event::getEndTime);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof UniMateCalendar)) {
            return false;
        }

        UniMateCalendar otherCalendar = (UniMateCalendar) other;
        return this.eventManager.equals(otherCalendar.eventManager);
    }
}
