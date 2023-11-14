package seedu.address.ui.events;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
//import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.event.Event;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays information of a {@code event}.
 */
public class EventCard extends UiPart<Region> {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private static final String FXML = "EventListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Event event;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label timeStart;
    @FXML
    private Label timeEnd;
    @FXML
    private Label eventLocation;
    @FXML
    private Label description;
    @FXML
    private FlowPane clients;

    /**
     * Creates a {@code eventCode} with the given {@code event} and index to display.
     */
    public EventCard(Event event, int displayedIndex) {
        super(FXML);
        this.event = event;
        id.setText(displayedIndex + ". ");
        name.setText(event.getEventName().value);
        timeStart.setText("Start: " + event.getDuration().getTimeStartValue());
        timeEnd.setText("End: " + event.getDuration().getTimeEndValue());
        eventLocation.setText(event.getLocation().value);
        description.setText(event.getDescription().value);
        event.getClients().stream()
                .sorted(Comparator.comparing(client -> client.getName().fullName))
                .forEach(client -> clients.getChildren().add(new Label(client.getName().fullName)));
    }
}
