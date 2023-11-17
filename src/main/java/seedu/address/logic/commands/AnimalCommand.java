package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AnimalModel;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 * Developers must implement {@link AnimalCommand#execute(AnimalModel)} which contains the logic of the command.
 */
public abstract class AnimalCommand {

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(AnimalModel model) throws CommandException;

}
