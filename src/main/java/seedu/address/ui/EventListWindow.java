package seedu.address.ui;

import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.event.Event;

/**
 * Controller for a help page
 */
public class EventListWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(EventListWindow.class);
    private static final String FXML = "EventListWindow.fxml";

    @FXML private TableView<Event> table;
    @FXML private TableColumn<Event, Void> indexCol;
    @FXML private TableColumn<Event, String> nameCol;
    @FXML private TableColumn<Event, String> startCol;
    @FXML private TableColumn<Event, String> endCol;
    @FXML private TableColumn<Event, String> locCol;
    @FXML private TableColumn<Event, String> infoCol;

    /**
     * Creates a new EventListWindow.
     *
     * @param root Stage to use as the root of the EventListWindow.
     */
    public EventListWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new EventListWindow.
     */
    public EventListWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show(ObservableList<Event> events) {
        logger.fine("Showing help page about the application.");

        indexCol.setCellFactory(col -> new TableIndexCell());
        nameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        startCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStartString()));
        endCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEndString()));
        locCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLocationStr()));
        infoCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getInformationStr()));

        table.setItems(events);

        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    class TableIndexCell extends TableCell<Event, Void> {
        @Override
        public void updateIndex(int index) {
            super.updateIndex(index);
            if (isEmpty() || index < 0) {
                setText(null);
            } else {
                setText(Integer.toString(index + 1));
            }
        }
    }
}
