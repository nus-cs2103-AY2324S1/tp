package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.event.Event;

/**
 * UI component that displays information of an expired event.
 */
public class ExpiredEventCard extends UiPart<Region> {

    private static final String FXML = "ExpiredEventCard.fxml";

    public final Event event;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label date;
    @FXML
    private Label timeDuration;
    @FXML
    private FlowPane names;

    /**
     * Creates a {@code ExpiredEventCard} with the given {@code Event} and index to display.
     * @param event
     * @param displayedIndex
     */
    public ExpiredEventCard(Event event, int displayedIndex) {
        super(FXML);
        this.event = event;
        id.setText(displayedIndex + ". ");
        name.setText(event.getName().name);
        date.setText(event.getStartDate().forDisplay());

        if (event.hasStartTime() && event.hasEndTime()) {
            timeDuration.setText(String.format("%s - %s",
                    event.getStartTime().forDisplay(),
                    event.getEndTime().forDisplay()));
        } else if (event.hasStartTime()) {
            timeDuration.setText(String.format("Start: %s",
                    event.getStartTime().forDisplay()));
        } else if (event.hasEndTime()) {
            timeDuration.setText(String.format("End: %s",
                    event.getEndTime().forDisplay()));
        } else {
            timeDuration.setText("");
        }

        event.getNames().stream()
                .sorted(Comparator.comparing(name -> name.fullName))
                .forEach(name -> names.getChildren().add(new Label(name.fullName)));
    }


}
