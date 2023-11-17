package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.AnimalMessages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AnimalModel;

/**
 * Resets all tasks of the animals in the catalog.
 */
public class ResetTaskCommand extends AnimalCommand {

    public static final String COMMAND_WORD = "reset";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Resets all tasks of the animal, identified by the index number "
            + "used in the displayed animal list, as uncompleted. ";

    public static final String EXAMPLE_USAGE = "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "All tasks are have been reset";

    /**
     * Returns the help message of this command.
     *
     * @return a string containing the help message of this command.
     */
    public static String getHelp() {
        return String.format(AnimalMessages.MESSAGE_HELP_FORMAT, MESSAGE_USAGE, EXAMPLE_USAGE);
    }

    @Override
    public CommandResult execute(AnimalModel model) throws CommandException {
        requireNonNull(model);

        model.resetTasks();

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
