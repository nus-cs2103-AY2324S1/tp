package seedu.address.model.event;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.TreeMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.event.exceptions.ConflictingEventException;
import seedu.address.model.event.exceptions.EventNotFoundException;

/**
 * Stores the event for a single day.
 */
public class SingleDayEventList {
    private final LocalDate date;
    private final NavigableMap<EventPeriod, Event> eventTree;

    /**
     * Constructs a SingleDayEventList to store events for the day.
     */
    public SingleDayEventList(LocalDate date) {
        requireNonNull(date);

        this.date = date;
        this.eventTree = new TreeMap<EventPeriod, Event>(EventPeriod::compareTo);
    }

    /**
     * Checks if adding an event is valid without causing conflicts with existing events.
     *
     * @param event The event to be added.
     * @return True if the event can be added without conflicts, false otherwise.
     */
    public boolean isEventAddValid(Event event) {
        requireNonNull(event);

        EventPeriod eventPeriod = event.getEventPeriod();

        Event precedingEvent = Optional.<Map.Entry<EventPeriod, Event>>ofNullable(this.eventTree.floorEntry(
                eventPeriod)).map(Map.Entry::getValue).orElse(Event.createNonConflictingEvent());
        Event proceedingEvent = Optional.<Map.Entry<EventPeriod, Event>>ofNullable(this.eventTree.ceilingEntry(
                eventPeriod)).map(Map.Entry::getValue).orElse(Event.createNonConflictingEvent());
        return !(precedingEvent.isConflicting(event) || proceedingEvent.isConflicting(event));
    }

    /**
     * Adds an event to the event tree.
     *
     * @param event The event to be added.
     */
    public void addEvent(Event event) {
        requireNonNull(event);
        if (!isEventAddValid(event)) {
            throw new ConflictingEventException();
        }
        eventTree.put(event.getEventPeriod(), event);
    }

    /**
     * Forcibly add the event to the event tree, disregarding time period conflict.
     *
     * @param event The event to be added.
     */
    public void forceAddEvent(Event event) {
        requireNonNull(event);
        eventTree.put(event.getEventPeriod(), event);
    }

    /**
     * Check if the event exists in the list.
     *
     * @param event event to be checked.
     * @return true if it exists in the list, false otherwise.
     */
    public boolean containsEvent(Event event) {
        requireNonNull(event);
        for (Event thisEvent : eventTree.values()) {
            if (event.equals(thisEvent)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if there is an event at specified time
     *
     * @param dateTime the specified time
     * @return an {@code @Optional} containing the event if there is an event at the time, empty optional otherwise.
     */
    public Optional<Event> eventAtTime(LocalDateTime dateTime) {
        requireNonNull(dateTime);
        for (Event event : eventTree.values()) {
            Event parentEvent = event.getParentEvent();
            if (parentEvent.isDuring(dateTime)) {
                return Optional.of(event.getParentEvent());
            }
        }
        return Optional.empty();
    }

    /**
     * Looks for all events within a time range and returns a List containing these events.
     *
     * @param eventPeriod The specified time range represented by a {@code EventPeriod}
     * @return A List object containing all events that are within the time range.
     */
    public List<Event> eventsInRange(EventPeriod eventPeriod) {
        ArrayList<Event> output = new ArrayList<Event>();
        for (Event thisEvent : eventTree.values()) {
            if (thisEvent.isPeriodConflicting(eventPeriod)) {
                output.add(thisEvent.getParentEvent());
            }
        }
        return output;
    }

    /**
     * Removes the event from the list. The event must exist in the list.
     * @param toRemove event to be removed.
     */
    public void remove(Event toRemove) {
        requireNonNull(toRemove);
        for (Event thisEvent : this.eventTree.values()) {
            if (toRemove.getParentEvent().equals(thisEvent.getParentEvent())) {
                this.eventTree.remove(thisEvent.getEventPeriod(), thisEvent);
                return;
            }
        }
        throw new EventNotFoundException();
    }

    /**
     * Returns the events for the day as an observableList.
     *
     * @return the events for the day as an observableList.
     */
    public ObservableList<Event> getDayEventList() {
        return FXCollections.<Event>observableArrayList(this.eventTree.values()
                .toArray(new Event[0]));
    }

    /**
     * Checks if there are no events present in this list.
     *
     * @return true if there are no events present, false otherwise.
     */
    public boolean isEmpty() {
        return eventTree.isEmpty();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof SingleDayEventList)) {
            return false;
        }

        SingleDayEventList otherDay = (SingleDayEventList) other;
        if (!(this.eventTree.size() == otherDay.eventTree.size())) {
            return false;
        }

        for (Event thisEvent : this.eventTree.values()) {
            if (!otherDay.containsEvent(thisEvent)) {
                return false;
            }
        }

        return true;
    }
}
