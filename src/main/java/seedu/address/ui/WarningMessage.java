package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a warning message window.
 */
public class WarningMessage extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(WarningMessage.class);
    private static final String FXML = "WarningMessage.fxml";

    @FXML
    private Button confirmButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label warningLabel;

    private boolean confirmed = false;

    /**
     * Creates a new WarningMessage window.
     */
    public WarningMessage() {
        this(new Stage());
    }

    /**
     * Creates a new WarningMessage window.
     *
     * @param root Stage to use as the root of the WarningMessage.
     */
    public WarningMessage(Stage root) {
        super(FXML, root);
    }

    /**
     * Shows the warning message window.
     */
    public void show() {
        logger.fine("Showing warning message.");
        getRoot().show();
    }

    /**
     * Returns true if the user confirmed the action, false otherwise.
     */
    public boolean isConfirmed() {
        return confirmed;
    }

    @FXML
    private void confirm() {
        confirmed = true;
        getRoot().close();
    }

    @FXML
    private void cancel() {
        confirmed = false;
        getRoot().close();
    }
}
