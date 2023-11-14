package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a command that can be undone. Subclasses of this abstract class
 * must provide an implementation for the undo operation.
 */
public abstract class UndoableCommand extends Command {
    /**
     * Reverts the effects of the command.
     *
     * @param model {@code Model} which the undo command should operate on.
     * @return feedback message of the undo result for display
     * @throws CommandException If an error occurs during the undo operation.
     */
    public abstract CommandResult undo(Model model) throws CommandException;

}
