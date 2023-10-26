package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;

/**
 * Controller for events window
 */
public class EventWindow extends UiPart<Stage> {
    private static final String FXML = "EventWindow.fxml";

    private static final Logger logger = LogsCenter.getLogger(EventWindow.class);

    private EventListPanel eventListPanel;

    @FXML
    private StackPane eventListPanelPlaceholder;

    /**
     * Initialises an EventWindow object
     */
    public EventWindow(Stage root, Logic logic) {
        super(FXML, root);
        eventListPanel = new EventListPanel(logic.getFilteredEventList());
        eventListPanelPlaceholder.getChildren().add(eventListPanel.getRoot());
    }

    /**
     * Shows the event window.
     */
    public void show() {
        logger.fine("Showing events page.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    public boolean isShowing() {
        return getRoot().isShowing();
    }

    public void focus() {
        getRoot().requestFocus();
    }


}
