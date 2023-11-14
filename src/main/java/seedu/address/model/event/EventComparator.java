package seedu.address.model.event;

import java.util.Comparator;

/**
 * Compares two events by their start time.
 */
public class EventComparator implements Comparator<Event> {
    @Override
    public int compare(Event event1, Event event2) {
        return event1.compareTo(event2);
    }
}
