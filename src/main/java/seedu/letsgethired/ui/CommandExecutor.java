package seedu.letsgethired.ui;

import seedu.letsgethired.logic.commands.CommandResult;
import seedu.letsgethired.logic.commands.exceptions.CommandException;
import seedu.letsgethired.logic.parser.exceptions.ParseException;

/**
 * Represents a function that can execute commands.
 */
@FunctionalInterface
public interface CommandExecutor {
    /**
     * Executes the command and returns the result.
     *
     * @see seedu.letsgethired.logic.Logic#execute(String)
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;
}
