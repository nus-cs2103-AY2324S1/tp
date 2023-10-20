package seedu.address.model.calendar;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;

import seedu.address.model.event.AllDaysEventListManager;
import seedu.address.model.event.Event;

/**
 * Represents a calendar that stores and manages events.
 */
public class Calendar implements ReadOnlyCalendar {
    private final AllDaysEventListManager eventManager;

    /**
     * Constructs a Calendar object with an empty event tree.
     */
    public Calendar() {
        this.eventManager = new AllDaysEventListManager();
    }

    /**
     * Creates a Calendar using the Events in the {@code toBeCopied}
     */
    public Calendar(ReadOnlyCalendar toBeCopied) {
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
    }

    /**
     * Checks if there is an event at specified time and deletes it if there is.
     * @param dateTime the specified time
     */
    public void deleteEventAt(LocalDateTime dateTime) {
        requireNonNull(dateTime);
        eventManager.deleteEventAt(dateTime);
    }

    /**
     * Looks for an event at specified time.
     * @param dateTime the specified time.
     * @return an optional containing the event if there is an event at the time, an empty optional otherwise.
     */
    public Optional<Event> findEventAt(LocalDateTime dateTime) {
        requireNonNull(dateTime);
        return eventManager.eventAt(dateTime);
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
    public ObservableList<Event> getEventList() {
        return eventManager.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Calendar)) {
            return false;
        }

        Calendar otherCalendar = (Calendar) other;
        return this.eventManager.equals(otherCalendar.eventManager);
    }
}
