package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import seedu.address.model.schedule.Schedule;

/**
 * An UI component that displays information of a {@code Schedule}.
 */
public class ScheduleCard extends UiPart<Region> {

    private static final String FXML = "ScheduleListCard.fxml";
    private static final Background MISSED_STATUS_BACKGROUND = new Background(new BackgroundFill(Color.RED,
        new CornerRadii(5), Insets.EMPTY));
    private static final Background COMPLETED_STATUS_BACKGROUND = new Background(new BackgroundFill(Color.GREEN,
        new CornerRadii(5), Insets.EMPTY));
    private static final Background PENDING_STATUS_BACKGROUND = new Background(new BackgroundFill(Color.GRAY,
        new CornerRadii(5), Insets.EMPTY));

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Schedule schedule;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label startTime;
    @FXML
    private Label endTime;
    @FXML
    private Label status;

    /**
     * Creates a {@code ScheduleCode} with the given {@code Schedule} and index to display.
     */
    public ScheduleCard(Schedule schedule, int displayedIndex) {
        super(FXML);
        this.schedule = schedule;
        id.setText(displayedIndex + ". ");
        name.setText(schedule.getTutor().getName().fullName);
        startTime.setText(schedule.getStartTime().toString());
        endTime.setText(schedule.getEndTime().toString());
        status.setText(schedule.getStatus().toString());
        switch (schedule.getStatus()) {
        case MISSED:
            status.setBackground(MISSED_STATUS_BACKGROUND);
            break;
        case COMPLETED:
            status.setBackground(COMPLETED_STATUS_BACKGROUND);
            break;
        case PENDING:
        default:
            status.setVisible(false);
        }
        status.setPadding(new Insets(2));
    }
}
