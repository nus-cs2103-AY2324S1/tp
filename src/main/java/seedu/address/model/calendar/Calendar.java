package seedu.address.model.calendar;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.TreeMap;

import seedu.address.model.event.Event;
import seedu.address.model.event.EventPeriod;

/**
 * Represents a calendar that stores and manages events.
 */
public class Calendar {
    private NavigableMap<EventPeriod, Event> eventTree;

    /**
     * Constructs a Calendar object with an empty event tree.
     */
    public Calendar() {
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
     * Adds an event to the calendar.
     *
     * @param event The event to be added.
     */
    public void addEvent(Event event) {
        requireNonNull(event);
        assert(isEventAddValid(event));

        this.eventTree.put(event.getEventPeriod(), event);
    }

    /**
     * Get the tree map storing the events.
     *
     * @return tree map data structure object storing events of this calendar.
     */
    public NavigableMap<EventPeriod, Event> getEventTree() {
        return eventTree;
    }

    /**
     * Checks if the given index is valid for accessing events.
     *
     * @param index The index to be checked.
     * @return True if the index is valid, false otherwise.
     */
    private boolean isIndexValid(int index) {
        return index <= this.eventTree.values().toArray().length;
    }

    /**
     * Retrieves the event at the specified index. Index specified must be a valid index.
     *
     * @param index The index of the event to retrieve.
     * @return The event at the specified index.
     */
    private Event getEventAtIndex(int index) {
        assert(isIndexValid(index));
        return this.eventTree.values().toArray(new Event[0])[index - 1];
    }

    /**
     * Edits the time period of an event at the specified index.
     *
     * @param index The index of the event to edit.
     * @param eventPeriod The new time period for the event.
     */
    public void editEventPeriod(int index, EventPeriod eventPeriod) {
        requireNonNull(eventPeriod);

        Event eventToEdit = getEventAtIndex(index);
        eventToEdit.changeEventPeriod(eventPeriod);
        removeEvent(index);
        addEvent(eventToEdit);
    }

    /**
     * Removes an event at the specified index from the calendar.
     *
     * @param index The index of the event to be removed.
     */
    public void removeEvent(int index) {
        assert(!this.eventTree.isEmpty());

        Event toBeRemoved = getEventAtIndex(index);
        assert(this.eventTree.containsValue(toBeRemoved));

        EventPeriod keyToEventToBeRemoved = toBeRemoved.getEventPeriod();
        this.eventTree.remove(keyToEventToBeRemoved);
    }

    /**
     * Clears the calendar
     */
    public void clear() {
        this.eventTree.clear();
    }

    /**
     * Check if the calendar contains the event.
     *
     * @param event event to be checked.
     * @return true if it is contained in the calendar, false otherwise.
     */
    public boolean contains(Event event) {
        return this.eventTree.containsValue(event);
    }

    /**
     * Checks if the calendar is empty.
     *
     * @return true if calendar is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.eventTree.isEmpty();
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
        return this.eventTree.equals(otherCalendar.eventTree);
    }
}
