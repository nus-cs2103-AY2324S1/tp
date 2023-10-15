package seedu.address.model.event;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of events in the address book. Operations for managing the list of events are implemented here.
 */
public class EventList {

    private ArrayList<Event> events = new ArrayList<>();

    /**
     * Adds an event to the list of events.
     * @param event Event to be added.
     */
    public void addEvent(Event event) {
        requireNonNull(event);
        this.events.add(event);
    }

    /**
     * Deletes an event from the list of events.
     * @param index index of the event to be deleted.
     */
    public void deleteEvent(int index) {
        return;
    }

    /**
     * Edit event in the list of events.
     * @param index index of the event to be edited.
     */
    public void editEvent(int index) {
        return;
    }

    public void setEvents(ArrayList<Event> newEvents) {
        this.events = newEvents;
    }

    public ArrayList<Event> getEventsList() {
        return this.events;
    }
}
