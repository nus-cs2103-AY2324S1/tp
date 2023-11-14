package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import seedu.address.commons.core.index.Index;



/**
 * An UI component that displays information of a calendar schedule.
 */
public class CalendarCard extends UiPart<Region> {
    private static final String FXML = "CalendarCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX. As a consequence, UI
     * elements' variable names cannot be set to such keywords or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    @FXML
    private VBox card;
    @FXML
    private Label index;
    @FXML
    private Label timeLabel;
    @FXML
    private Label status;


    /**
     * Creates a {@code CalendarCard} with the given status, time label and index to display.
     */
    public CalendarCard(Index index, String timeLabel, String status,
        double width, double translateX, Color color) {
        super(FXML);

        card.setBackground(
            new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));

        card.setAlignment(Pos.CENTER);
        card.setMinHeight(50);
        card.setMinWidth(width);
        card.setMaxWidth(width);
        card.setTranslateX(translateX);

        this.index.setText(index.getOneBased() + "");
        this.timeLabel.setText(timeLabel);
        this.status.setText(status);
    }
}
