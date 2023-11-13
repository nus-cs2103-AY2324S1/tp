package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.lead.Lead;
import seedu.address.model.reminder.Reminder;

/**
 * An UI component that displays information of a {@code Reminder}.
 */
public class ReminderCard extends UiPart<Region> {

    private static final String FXML = "ReminderCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Reminder reminder;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label followUpDate;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane lead;

    /**
     * Creates a {@code ReminderCard} with the given {@code Reminder}.
     */
    public ReminderCard(Reminder reminder) {
        super(FXML);
        this.reminder = reminder;
        name.setText(reminder.getName().fullName);
        followUpDate.setText(reminder.getFollowUpDate().toString());

        Lead reminderLead = reminder.getLead();

        if (reminderLead != null) {
            Label leadLabel = new Label(reminder.getLead().toString());

            if (reminderLead.isHot()) {
                leadLabel.getStyleClass().add("hot-lead");
            } else if (reminderLead.isWarm()) {
                leadLabel.getStyleClass().add("warm-lead");
            } else if (reminderLead.isCold()) {
                leadLabel.getStyleClass().add("cold-lead");
            }

            lead.getChildren().add(leadLabel);
        }
    }
}
