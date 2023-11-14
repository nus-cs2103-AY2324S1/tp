package seedu.application.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.application.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2324s1-cs2103t-w12-3.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE_COMMANDS =
        "Here are the available commands and how to use them:\n\n"
            + "Words in UPPER_CASE are the parameters to be supplied by the user\n\n"
            + "1. To add an application: add c/COMPANY r/ROLE\n"
            + "2. To edit an application: edit INDEX\n"
            + "3. To delete an application: delete INDEX \n"
            + "4. To list all applications: list \n"
            + "5. To find an application: find [KEYWORDS]\n"
            + "6. To sort applications: sort PREFIX\n"
            + "7. To add an interview to an application: interview add INDEX t/TYPE d/DATETIME a/ADDRESS\n"
            + "8. To edit an interview of an application: interview edit INTERVIEW_INDEX from/JOB_INDEX\n"
            + "9. To delete an interview from an application: interview delete INTERVIEW_INDEX from/JOB_INDEX\n"
            + "10. To clear all applications: clear\n"
            + "11. To exit the app: exit\n";
    public static final String HELP_MESSAGE =
        HELP_MESSAGE_COMMANDS + "\n"
            + "Refer to the user guide for additional optional inputs: " + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     *
     * @throws IllegalStateException <ul>
     *                               <li>
     *                               if this method is called on a thread other than the JavaFX Application Thread.
     *                               </li>
     *                               <li>
     *                               if this method is called during animation or layout processing.
     *                               </li>
     *                               <li>
     *                               if this method is called on the primary stage.
     *                               </li>
     *                               <li>
     *                               if {@code dialogStage} is already showing.
     *                               </li>
     *                               </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
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

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
