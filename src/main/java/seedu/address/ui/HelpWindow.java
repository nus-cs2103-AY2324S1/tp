package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2324s1-cs2103t-t11-1.github.io/tp/UserGuide.html";
    public static final String COMMAND_TABLE = "Command Table" + "\n" +
            "1. Add: add n/NAME p/PHONE_NUMBER e/EMAIL c/CLASS_NUMBER [t/TAG]…​\n" +
            "2. Comment: comment s/STUDENT_NUMBER c/COMMENT\n" +
            "3. Delete: delete s/STUDENT_NUMBER\n" +
            "4. Edit: edit STUDENT_NUMBER [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [s/NEW_STUDENT_NUMBER] [c/CLASS_NUMBER]\n" +
            "5. Tag: tag STUDENT_NUMBER [/add] [/delete] t/[TAG]…​\n" +
            "6. Lookup: lookup [c/CLASS_NUMBER] [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [s/STUDENT_NUMBER] [t/TAG]\n" +
            "7. Config: config #t/TUTORIAL_COUNT #a/ASSIGNMENT_COUNT\n" +
            "8. Mark: mark TUTORIAL_INDEX s/STUDENT_NUMBER\n" +
            "9. Markall: markall TUTORIAL_INDEX\n" +
            "10. Set Grade: set-grade s/STUDENT_NUMBER a/ASSIGNMENT_INDEX g/GRADE\n" +
            "11. Record Part: record-part s/STUDENT_NUMBER tut/TUTORIAL_INDEX part/PARTICIPATION_LEVEL\n" +
            "12. View: view STUDENT_NUMBER\n" +
            "13. Load: load f/FILE_NAME\n" +
            "14. Clear: clear\n" +
            "15. List: list\n" +
            "16. Help: help\n" +
            "17. Exit: exit\n" +
            "18. Theme: theme";


    public static final String HELP_MESSAGE = COMMAND_TABLE + "\nRefer to the user guide: " + USERGUIDE_URL;

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
