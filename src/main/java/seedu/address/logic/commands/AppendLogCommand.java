package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

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

    public static final String MESSAGE_USAGE = COMMAND_WORD + " or " + COMMAND_WORD_ALIAS
            + ": Appends the results of the most recent FindCommand to the logger tab.\n"
            + "Example 1: " + COMMAND_WORD + "\n"
            + "Example 2: " + COMMAND_WORD_ALIAS;

    public static final String MESSAGE_SUCCESS = "The last filtered values have been added onto the logger tab.";

    public static final String MESSAGE_DUPLICATES =
            "Person(s) already in list: %1$s\nThey were not appended to the log.";

    public static final String MESSAGE_FAILURE = "Cannot log an empty list.";

    public static final String MESSAGE_UNDO_ALOG_SUCCESS = "Undoing the appending to log.";

    private LogBook logBookBeforeAppend;

    /**
     * Executes the AppendLogCommand, appending the results of the most recent FindCommand to the logbook.
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
        logBookBeforeAppend = new LogBook(model.getLogBook());
        model.addToHistory(this);


        boolean hasDupes = false;
        String duplicateClause = "";
        for (Person person : model.getFoundPersonsList()) {
            if (model.getLogBook().hasPerson(person)) {
                hasDupes = true;
                duplicateClause += "\n  " + person.getName() + ", ID: " + person.getId();
                continue;
            }
            model.getLogBook().addPerson(person);
        }

        return hasDupes
                ? new CommandResult(String.format(MESSAGE_DUPLICATES, duplicateClause))
                : new CommandResult(MESSAGE_SUCCESS);

    }

    /**
     * Undoes the AppendLogCommand, restoring the logbook to its state before the appending.
     *
     * @param model The current model that contains the data.
     * @return A CommandResult indicating the success of the undo operation.
     */
    @Override
    public CommandResult undo(Model model) {
        requireNonNull(model);
        model.setLogBook(logBookBeforeAppend);
        return new CommandResult(MESSAGE_UNDO_ALOG_SUCCESS);
    }
}
