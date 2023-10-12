package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.event.Notification;

import java.util.logging.Logger;

/**
 * Controller for a help page
 */
public class NotificationWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(NotificationWindow.class);
    private static final String FXML = "NotificationWindow.fxml";

    @FXML
    private Label notificationTitle;

    @FXML
    private Label notificationDescription;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public NotificationWindow(Stage root, Stage ownerStage, Notification notification) {
        super(FXML, root);
        root.initOwner(ownerStage);
        notificationTitle.setText(String.format("Upcoming Event: %s", notification.getTitle()));
        notificationDescription.setText(notification.getDescription());
    }

    /**
     * Creates a new NotificationWindow.
     */
    public NotificationWindow(Stage ownerStage, Notification notification) {
        this(new Stage(), ownerStage, notification);
    }

    /**
     * Shows the notification window.
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
    public void show() {
        logger.fine("Showing a notification");
        getRoot().centerOnScreen();
        getRoot().show();
    }
}
