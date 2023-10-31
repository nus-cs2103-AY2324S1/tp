package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.LogBook;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Appends the results of a FindCommand to the logbook.
 */
public class AppendLogCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "alog";

    public static final String COMMAND_WORD_ALIAS = "al";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "or" + COMMAND_WORD_ALIAS
            + ": Appends the results of the most recent FindCommand to the logger tab.\n"
            + "Example 1: " + COMMAND_WORD + "\n"
            + "Example 2: " + COMMAND_WORD_ALIAS;

    public static final String MESSAGE_SUCCESS = "Results of the FindCommand have been appended to the logger tab.";

    public static final String MESSAGE_UNDO_ALOG_SUCCESS = "Undoing the appending to log.";

    private LogBook logBookBeforeAppend;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // Ensure that there are results from the most recent FindCommand
        if (model.getFoundPersonsList().isEmpty()) {
            throw new CommandException(Messages.MESSAGE_EMPTY_FIND_RESULT);
        }

        // Store a copy of the current logBook before updating it
        logBookBeforeAppend = new LogBook(model.getLogBook());
        model.addToHistory(this);

        for (Person person : model.getFoundPersonsList()) {
            model.getLogBook().addPerson(person);
        }
        return new CommandResult(MESSAGE_SUCCESS);

    }

    @Override
    public CommandResult undo(Model model) {
        requireNonNull(model);
        model.setLogBook(logBookBeforeAppend);
        return new CommandResult(MESSAGE_UNDO_ALOG_SUCCESS);
    }
}
