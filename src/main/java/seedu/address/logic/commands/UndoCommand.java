package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Undoes the last command
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Undo Completed";
    public static final String MESSAGE_USAGE = "This command will undo the last command";
    public static final String MESSAGE_UNDO_DONE = "No more undo history found!";


    @Override
    public CommandResult execute(Model model) {
        if (model == null) {
            return new CommandResult(MESSAGE_UNDO_DONE);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
