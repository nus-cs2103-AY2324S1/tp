package networkbook.logic.commands;

import networkbook.logic.commands.exceptions.CommandException;
import networkbook.model.Model;

/**
 * Clears the network book.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_SUCCESS = "Noted, previously undone state change has been redone!";
    public static final String MESSAGE_REDO_DISALLOWED = "Illegal state change requested.\n"
            + "NetworkBook does not have a previous state stored to undo to.";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert model != null : "Model should not be null";
        model.redoNetworkBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
