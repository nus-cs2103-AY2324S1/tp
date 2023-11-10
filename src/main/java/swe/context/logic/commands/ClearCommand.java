package swe.context.logic.commands;

import swe.context.logic.Messages;
import swe.context.model.Model;



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
