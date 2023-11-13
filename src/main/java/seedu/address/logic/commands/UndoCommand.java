package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Reverts the {@code model}'s address book to its previous state.
 * @author {damithc}-reused
 *     adapted from <a href="https://github.com/se-edu/addressbook-level4">ab4</a>.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Reverses the effect of the most recent command.\n"
            + "No parameters are required.";
    public static final String MESSAGE_SUCCESS = "Last command has been undone!";
    public static final String MESSAGE_FAILURE = "No commands to undo!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasHistory()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.undo();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
