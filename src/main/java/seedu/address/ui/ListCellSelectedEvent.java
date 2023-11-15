package seedu.address.ui;

import javafx.event.Event;
import javafx.event.EventType;
import seedu.address.model.person.Person;

/**
 * The {@code ListCellSelectedEvent} class represents an event that is fired when a list cell is selected.
 * It provides information about the selected person.
 */
public class ListCellSelectedEvent extends Event {
    /**
     * The event type for list cell selected events.
     */
    public static final EventType<ListCellSelectedEvent> LIST_CELL_SELECTED =
            new EventType<>(Event.ANY, "LIST_CELL_SELECTED");

    private final Person selectedPerson;

    /**
     * Constructs a new {@code ListCellSelectedEvent} with the specified selected person.
     *
     * @param selectedPerson The selected person.
     */
    public ListCellSelectedEvent(Person selectedPerson) {
        super(LIST_CELL_SELECTED);
        this.selectedPerson = selectedPerson;
    }

    /**
     * Gets the selected person associated with this event.
     *
     * @return The selected person.
     */
    public Person getSelectedPerson() {
        return selectedPerson;
    }
}
