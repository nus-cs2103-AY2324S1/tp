package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.event.Event;

/**
 * A pop-up window that shows an event list of a specific person in the addressbook.
 */
public class EventListContainer extends UiPart<Region> {
    private static final String FXML = "EventListContainer.fxml";

    @FXML
    private StackPane eventListPlaceholder;

    private final EventListPanel eventListPanel;

    /**
     * Constructs an EventListContainer with the given event list
     */
    public EventListContainer(ObservableList<Event> eventList) {
        super(FXML);
        this.eventListPanel = new EventListPanel(eventList);
        fillInnerPart();
    }

    /**
     * Fills up the placeholder of this window
     */
    void fillInnerPart() {
        eventListPlaceholder.getChildren().add(eventListPanel.getRoot());
    }
}
