package seedu.address.model.event;

import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

import java.util.ArrayList;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a list of events in the address book. Operations for managing the list of events are implemented here.
 */
public class EventList {

    private ArrayList<Event> events = new ArrayList<>();

    public ArrayList<Event> getEventList() {
        return this.events;
    }

    /**
     * Adds an event to the list of events.
     * @param event Event to be added.
     */
    public void add(Event event) {
        requireAllNonNull(event);
        if (this.events.contains(event)) {
            throw new DuplicatePersonException();
        }
        this.events.add(event);
    }

    /**
     * Deletes an event from the list of events.
     * @param index index of the event to be deleted.
     */
    public void deleteEvent(int index) {
        return;
    }

    public void setEvent(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);

        int index = this.events.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        this.events.set(index, editedEvent);
    }
}
