package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_SUCCESS = "Redo Successful. ";
    public static final String MESSAGE_NO_COMMAND_TO_REDO_ERROR = "No command to redo.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Restores state to before the last undo command. "
            + "\n"
            + "Example: " + COMMAND_WORD;
    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!model.getUserHistoryManager().canRedo()) {
            throw new CommandException(MESSAGE_NO_COMMAND_TO_REDO_ERROR);
        } else {
            model.redoHistory();
            return new CommandResult("Command redone.", false, false, false);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RedoCommand); // instanceof handles nulls
    }
}
