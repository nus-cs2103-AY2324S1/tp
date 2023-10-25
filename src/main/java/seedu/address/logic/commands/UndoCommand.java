package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Undos the previous command.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "Previous command undid successfully!";

    @Override
    public CommandResult execute(Model model) {
        model.undo();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
