package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ConfirmClearCommand;
import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Controller for the clear command confirmation page.
 */
public class ClearWindow extends UiPart<Stage> {

    public static final String CONFIRM_CLEAR_MESSAGE = "Are you sure you want to clear all entries?";

    private static final Logger logger = LogsCenter.getLogger(ClearWindow.class);
    private static final String FXML = "ClearWindow.fxml";
    private CommandExecutor commandExecutor;

    @FXML
    private Label clearMessage;
    @FXML
    private Button clearButton;
    @FXML
    private Button cancelButton;

    /**
     * Creates a new ClearWindow.
     *
     * @param root Stage to use as the root of the ClearWindow.
     */
    public ClearWindow(Stage root) {
        super(FXML, root);
        root.initStyle(StageStyle.UNDECORATED);
        root.initModality(Modality.APPLICATION_MODAL);
        clearMessage.setText(CONFIRM_CLEAR_MESSAGE);
    }

    /**
     * Creates a new ClearWindow.
     */
    public ClearWindow(CommandExecutor commandExecutor) {
        this(new Stage());
        this.commandExecutor = commandExecutor;
    }

    /**
     * Shows the clear window.
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
     * Returns true if the clear window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the clear window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the clear window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
    /**
     * Clears the address book.
     */
    @FXML
    private void confirmClear() {
        Command clearCommand = new ConfirmClearCommand();
        try {
            commandExecutor.execute(clearCommand);
        } catch (CommandException e) {
            // nothing to do in this window if there's an error.
        }
        hide();
    }
    /**
     * Cancels the clear command.
     */
    @FXML
    private void cancel() {
        hide();
    }
    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.address.logic.Logic#execute(Command)
         */
        CommandResult execute(Command command) throws CommandException;
    }
}
