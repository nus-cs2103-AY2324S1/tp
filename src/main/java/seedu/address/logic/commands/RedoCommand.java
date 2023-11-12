package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Reverts the {@code model}'s address book to its previously undone state.
 * @author {damithc}-reused
 *     adapted from <a href="https://github.com/se-edu/addressbook-level4">ab4</a>.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Restores the effect of the most recent undone command.\n"
            + "No parameters are required.";
    public static final String MESSAGE_SUCCESS = "Last undo has been redone!";
    public static final String MESSAGE_FAILURE = "No commands to redo!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.canRedo()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.redo();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
