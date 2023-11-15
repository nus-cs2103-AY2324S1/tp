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
public class FeedbackWindow extends UiPart<Stage> {

    public static final String FEEDBACK_URL = "https://forms.gle/ymKYKfXse5PoPhvW6";
    public static final String FEEDBACK_MESSAGE = "Please provide your feedback by using this link: " + FEEDBACK_URL;

    private static final Logger logger = LogsCenter.getLogger(FeedbackWindow.class);
    private static final String FXML = "FeedbackWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label feedbackMessage;

    /**
     * Creates a new FeedbackWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public FeedbackWindow(Stage root) {
        super(FXML, root);
        feedbackMessage.setText(FEEDBACK_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public FeedbackWindow() {
        this(new Stage());
    }

    /**
     * Shows the Feedback window.
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
        logger.fine("Showing feedback page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the feedback window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the feedback window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the feedback window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the feedback survey to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(FEEDBACK_URL);
        clipboard.setContent(url);
    }

    /**
     * Sets the theme for the FeedbackWindow based on the specified theme setting.
     *
     * @param isLight A boolean indicating whether the light theme should be applied.
     *                If true, the light theme will be set; if false, the dark theme will be set.
     */
    public void setTheme(boolean isLight) {
        if (isLight) {
            // Load the light theme CSS
            String lightThemePath = getClass().getResource("/view/FeedbackWindowLight.css").toExternalForm();
            getRoot().getScene().getStylesheets().clear();
            getRoot().getScene().getStylesheets().add(lightThemePath);
        } else {
            // Load the dark theme CSS
            String darkThemePath = getClass().getResource("/view/FeedbackWindowDark.css").toExternalForm();
            getRoot().getScene().getStylesheets().clear();
            getRoot().getScene().getStylesheets().add(darkThemePath);
        }
    }
}
