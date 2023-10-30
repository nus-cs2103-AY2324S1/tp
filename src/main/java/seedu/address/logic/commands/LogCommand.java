package seedu.address.logic.commands;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Saves the results of a FindCommand to the logbook.
 */
public class LogCommand extends Command {

    public static final String COMMAND_WORD = "log";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Saves the results of the most recent FindCommand to the logbook.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Results of the FindCommand have been saved to the logbook.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // Ensure that there are results from the most recent FindCommand
        if (model.getFoundPersonsList().isEmpty()) {
            throw new CommandException(Messages.MESSAGE_EMPTY_FIND_RESULT);
        }

        model.getLogBook().setPersons(model.getFoundPersonsList());

        return new CommandResult(MESSAGE_SUCCESS);

    }
}
