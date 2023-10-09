package seedu.address.model.event;

import java.util.Comparator;

public class EventComparator implements Comparator<Event> {
    public int compare(Event event1, Event event2) {
        if (event1.getDateTime().isBefore(event2.getDateTime())) {
            return 1;
        } else if (event1.getDateTime().isAfter(event2.getDateTime())) {
            return -1;
        } else {
            return 0;
        }
    }
}
