package seedu.letsgethired.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.letsgethired.model.Model;

/**
 * Undoes the latest change to the intern tracker.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS_UNDONE = "Undid latest command";
    public static final String MESSAGE_SUCCESS_AT_LATEST_CHANGE = "Already at latest change!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        boolean isUndone = model.undoAction();
        if (!isUndone) {
            return new CommandResult(MESSAGE_SUCCESS_AT_LATEST_CHANGE);
        }
        return new CommandResult(MESSAGE_SUCCESS_UNDONE);
    }
}
