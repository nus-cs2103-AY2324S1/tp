package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.LogBook;
import seedu.address.model.Model;

/**
 * Clears the logger tab.
 */
public class ClearLogCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "clog";

    public static final String COMMAND_WORD_ALIAS = "cl";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " or " + COMMAND_WORD_ALIAS
            + ": Clears the entire logger tab.\n"
            + "Example 1: " + COMMAND_WORD + "\n"
            + "Example 2: " + COMMAND_WORD_ALIAS;

    public static final String MESSAGE_SUCCESS = "Logger tab has been cleared!";

    public static final String MESSAGE_FAILURE = "Cannot clear an empty log.";

    public static final String MESSAGE_UNDO_CLOG_SUCCESS = "Undoing clearing of the logging.";

    private LogBook logBookBeforeClear;

    /**
     * Executes the ClearLogCommand, clearing the entire logger tab.
     *
     * @param model The current model that contains the data.
     * @return A CommandResult indicating the success of the operation.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getLogBook().getPersonList().isEmpty()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        // Store a copy of the current logBook before updating it
        logBookBeforeClear = new LogBook(model.getLogBook());
        model.addToHistory(this);

        model.setLogBook(new LogBook());
        return new CommandResult(MESSAGE_SUCCESS);

    }

    /**
     * Undoes the ClearLogCommand, restoring the logger tab to its state before clearing.
     *
     * @param model The current model that contains the data.
     * @return A CommandResult indicating the success of the undo operation.
     */
    @Override
    public CommandResult undo(Model model) {
        requireNonNull(model);
        model.setLogBook(logBookBeforeClear);
        return new CommandResult(MESSAGE_UNDO_CLOG_SUCCESS);
    }
}
