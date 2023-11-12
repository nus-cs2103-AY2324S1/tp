package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.event.Event;
import seedu.address.model.event.UniqueEventList;

/**
 * Wraps all data at the event-book level
 */
public class EventBook implements ReadOnlyEventBook {
    private final UniqueEventList events;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        events = new UniqueEventList();
    }

    public EventBook() {}

    /**
     * Creates an EventBook using the Events in the {@code toBeCopied}
     */
    public EventBook(ReadOnlyEventBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the event list with {@code events}.
     */
    public void setEvents(List<Event> events) {
        this.events.setEvents(events);
    }

    /**
     * Resets the existing data of this {@code EventBook} with {@code newData}.
     */
    public void resetData(ReadOnlyEventBook newData) {
        requireNonNull(newData);
        setEvents(newData.getEventList());
    }

    //// event-level operations
    public void addEvent(Event e) {
        events.add(e);
    }

    public void setEvent(Event target, Event editedEvent) {
        events.setEvent(target, editedEvent);
    }

    /**
     * Removes {@code key} from this {@code EventBook}.
     * {@code key} must exist in the event book.
     */
    public void removeEvent(Event key) {
        events.remove(key);
    }

    public boolean hasEvent(Event event) {
        return events.contains(event);
    }

    /**
     * Sorts the event book using the provided comparator.
     *
     * @param comparator The comparator used to determine the sorting order.
     */
    public void sortEventBook(Comparator<Event> comparator) {
        ObservableList<Event> eventObservableList = getEventList();
        List<Event> sortedList = eventObservableList.sorted(comparator);
        setEvents(sortedList);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("events", events)
                .toString();
    }

    @Override
    public ObservableList<Event> getEventList() {
        return events.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventBook)) {
            return false;
        }

        EventBook otherEventBook = (EventBook) other;
        return events.equals(otherEventBook.events);
    }
}
