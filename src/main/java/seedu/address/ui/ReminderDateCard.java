package seedu.address.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.reminder.Reminder;

/**
 * An UI component that displays information of a {@code Reminder}.
 */
public class ReminderDateCard extends UiPart<Region> {

    private static final String FXML = "ReminderDateCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on CompanyList level 4</a>
     */

    public final LocalDate reminderDate;

    @FXML
    private HBox dateCardPane;
    @FXML
    private Label date;
    @FXML
    private ListView<Reminder> reminderListView;

    /**
     * Creates a {@code CompanyCard} with the given {@code Company} and index to display.
     */
    public ReminderDateCard(LocalDate reminderDate, ObservableList<Reminder> dateSpecificReminders) {
        super(FXML);
        this.reminderDate = reminderDate;
        date.setText(reminderDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));

        reminderListView.setItems(dateSpecificReminders);
        reminderListView.setCellFactory(listView -> new ReminderDateCard.ReminderCardListViewCell());
        reminderListView.setPrefHeight(100 * dateSpecificReminders.size());
    }

    static class ReminderCardListViewCell extends ListCell<Reminder> {
        @Override
        protected void updateItem(Reminder reminder, boolean empty) {
            super.updateItem(reminder, empty);

            if (empty || reminder == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ReminderCard(reminder).getRoot());
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ReminderDateCard)) {
            return false;
        }

        // state check
        ReminderDateCard card = (ReminderDateCard) other;
        return date.getText().equals(card.date.getText());
    }
}
