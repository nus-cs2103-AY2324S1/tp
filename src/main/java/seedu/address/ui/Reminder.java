package seedu.address.ui;

import javafx.geometry.Rectangle2D;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;
import seedu.address.model.Model;

/**
 * Reminder class that displays a dialog box with the user's reminded events and birthdays.
 * The dialog box contains two sections, one for birthdays and one for schedule events.
 * The dialog box is positioned at the bottom right corner of the primary screen.
 */
public class Reminder {
    /**
     * Displays a dialog box with reminders for birthdays and scheduled events.
     *
     * @param model The model containing the user's data.
     * @param primaryStage The primary stage of the application.
     */
    public static void showReminder(Model model, Stage primaryStage) {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Reminders");

        String remindedEvents = model.getUser().returnRemindedEvent();

        String remindedBirthday = model.getBirthdayList();

        // Create the first header label and content
        Label header1 = new Label("Birthday");
        header1.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");
        TextArea content1 = new TextArea(remindedBirthday);
        content1.setStyle("-fx-font-size: 12px;");
        content1.setEditable(false);
        content1.setPrefColumnCount(25); // Adjust the preferred column count
        content1.setPrefRowCount(3); // Adjust the preferred row count

        // Create the second header label and content
        Label header2 = new Label("Schedule");
        header2.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");
        TextArea content2 = new TextArea(remindedEvents);
        content2.setStyle("-fx-font-size: 12px;");
        content2.setEditable(false);
        content2.setPrefColumnCount(25); // Adjust the preferred column count
        content2.setPrefRowCount(8); // Adjust the preferred row count

        // Create a VBox to hold the components
        VBox dialogVBox = new VBox(header1, content1, header2, content2);
        dialog.getDialogPane().setContent(dialogVBox);

        // Add OK and Cancel buttons
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Get the primary screen dimensions
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // Calculate the position for the bottom right corner
        double x = bounds.getMaxX() - dialog.getDialogPane().getPrefWidth() - 340;
        double y = bounds.getMaxY() - dialog.getDialogPane().getPrefHeight() - 390;

        // Ensure the dialog stays within the screen boundaries
        if (x < bounds.getMinX()) {
            x = bounds.getMinX();
        }
        if (y < bounds.getMinY()) {
            y = bounds.getMinY();
        }

        // Set the dialog's position
        dialog.initOwner(primaryStage);
        Window window = dialog.getDialogPane().getScene().getWindow();
        window.setX(x);
        window.setY(y);

        // Show the dialog
        dialog.showAndWait();
    }
}
