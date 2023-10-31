package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.LogBook;
import seedu.address.model.Model;

/**
 * Saves the results of a FindCommand to the logbook.
 */
public class LogCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "log";

    public static final String COMMAND_WORD_ALIAS = "lo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "or" + COMMAND_WORD_ALIAS
            + ": Saves the results of the most recent FindCommand to the logger tab.\n"
            + "Example 1: " + COMMAND_WORD + "\n"
            + "Example 2: " + COMMAND_WORD_ALIAS;

    public static final String MESSAGE_SUCCESS = "Results of the FindCommand have been saved to the logger tab.";

    public static final String MESSAGE_UNDO_LOG_SUCCESS = "Undoing the logging.";

    private LogBook logBookBeforeUpdate;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // Ensure that there are results from the most recent FindCommand
        if (model.getFoundPersonsList().isEmpty()) {
            throw new CommandException(Messages.MESSAGE_EMPTY_FIND_RESULT);
        }

        // Store a copy of the current logBook before updating it
        logBookBeforeUpdate = new LogBook(model.getLogBook());
        model.addToHistory(this);

        model.getLogBook().setPersons(model.getFoundPersonsList());
        return new CommandResult(MESSAGE_SUCCESS);

    }

    @Override
    public CommandResult undo(Model model) {
        requireNonNull(model);
        model.setLogBook(logBookBeforeUpdate);
        return new CommandResult(MESSAGE_UNDO_LOG_SUCCESS);
    }
}
