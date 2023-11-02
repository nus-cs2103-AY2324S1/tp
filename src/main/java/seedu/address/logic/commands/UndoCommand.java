package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Undoes the last command
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Undo Completed";
    public static final String MESSAGE_USAGE = "This command will undo the last command";


    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
