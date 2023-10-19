package seedu.address.logic.commands;

import seedu.address.logic.Messages;
import seedu.address.model.Model;



/**
 * Removes all {@link Contact}s.
 */
public class ClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";

    @Override
    public CommandResult execute(Model model) {
        model.removeAllContacts();

        return new CommandResult(Messages.COMMAND_CLEAR_SUCCESS);
    }
}
