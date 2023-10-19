package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Attempts to clear the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String CONFIRM_CLEAR_MESSAGE = "Please confirm the command.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(CONFIRM_CLEAR_MESSAGE, false, false, true);
    }
}
