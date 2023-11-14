package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.LogBook;
import seedu.address.model.Model;

/**
 * Saves the results of a FindCommand to the logbook.
 */
public class LogCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "log";

    public static final String COMMAND_WORD_ALIAS = "lo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " or " + COMMAND_WORD_ALIAS
            + ": Saves the results of the most recent FindCommand to the logger tab.\n"
            + "Example 1: " + COMMAND_WORD + "\n"
            + "Example 2: " + COMMAND_WORD_ALIAS;

    public static final String MESSAGE_SUCCESS = "The last filtered values have overridden the logger tab.";

    public static final String MESSAGE_FAILURE = "Cannot log an empty list.";

    public static final String MESSAGE_UNDO_LOG_SUCCESS = "Undoing the logging.";

    private LogBook logBookBeforeUpdate;

    /**
     * Executes the LogCommand, saving the results of the most recent FindCommand to the logger tab.
     *
     * @param model The current model that contains the data.
     * @return A CommandResult indicating the success of the operation.
     * @throws CommandException If there are no results from the most recent FindCommand.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {

        // Ensure that there are results from the most recent FindCommand
        if (model.getFoundPersonsList().isEmpty()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        // Store a copy of the current logBook before updating it
        logBookBeforeUpdate = new LogBook(model.getLogBook());
        model.addToHistory(this);

        model.getLogBook().setPersons(model.getFoundPersonsList());

        return new CommandResult(MESSAGE_SUCCESS);

    }

    /**
     * Undoes the LogCommand, restoring the logger tab to its state before saving the results.
     * @param model The current model that contains the data.
     * @return A CommandResult indicating the success of the undo operation.
     */
    @Override
    public CommandResult undo(Model model) {
        requireNonNull(model);
        model.setLogBook(logBookBeforeUpdate);
        return new CommandResult(MESSAGE_UNDO_LOG_SUCCESS);
    }
}
