package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TAGS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists all tags in the address book.
 */
public class ListTCommand extends Command {
    public static final String COMMAND_WORD = "listT";

    public static final String MESSAGE_SUCCESS = "Listed all tags";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all tags. "
            + "Example: " + COMMAND_WORD;

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredTagList(PREDICATE_SHOW_ALL_TAGS);
        return new CommandResult(MESSAGE_SUCCESS, false, false, false, false, true);
    }
}
