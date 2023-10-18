package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.event.exceptions.EventNotFoundException;

/**
 * Represents a list of events in the address book. Operations for managing the list of events are implemented here.
 */
public class EventList {

    private final ObservableList<Event> internalList = FXCollections.observableArrayList();
    private final ObservableList<Event> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Adds an event to the list of events.
     * @param event Event to be added.
     */
    public void addEvent(Event event) {
        requireNonNull(event);
        this.internalList.add(event);
    }

    /**
     * Replaces the given event {@code target} in the list with {@code editedEvent}.
     * @param target event to be edited. {@code target} must exist in the address book.
     * @param editedEvent event with the edited details.
     */
    public void setEvent(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);

        int index = this.internalList.indexOf(target);
        if (index == -1) {
            throw new EventNotFoundException(); // Change to event exception
        }

        this.internalList.set(index, editedEvent);
    }

    /**
     * Returns the event List
     * @return ArrayList of events.
     */
    public List<Event> getEventsList() {
        return this.internalList;
    }

    public void setEvents(List<Event> newEvents) {
        this.internalList.setAll(newEvents);
    }

    public ObservableList<Event> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Removes the equivalent event from the list.
     * @param target Event to be removed.
     */
    public void remove(Event target) {
        requireNonNull(target);
        if (!this.internalList.remove(target)) {
            throw new EventNotFoundException();
        }
    }
}
