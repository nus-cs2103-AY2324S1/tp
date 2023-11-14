package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.event.Event;

/**
 * This class implements a read only event book that is unmodifiable.
 */
public interface ReadOnlyEventsBook {
    /**
     * Returns an unmodifiable view of the events list.
     * This list will not contain any duplicate events.
     */
    ObservableList<Event> getEventList();
}
