package seedu.address.ui;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class EventCard extends UiPart<Region> {

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
    private Label description;
    @FXML
    private HBox cardPane;
    @FXML
    private Label index;
    @FXML
    private Label id;
    @FXML
    private Label start_time;
    @FXML
    private Label end_time;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public EventCard(Event event, int displayedIndex) {
        super(FXML);
        this.event = event;
        description.setText(event.getDescription());
        start_time.setText(event.getStart_time().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        end_time.setText(event.getEnd_time().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
    }
}
