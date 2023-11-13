package seedu.address.logic.commands;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.card.Card;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;


    /**
     * Checks if the provided index is invalid for the given list.
     * The index is considered invalid if it is negative, not equal to Index.RANDOM,
     * and greater than or equal to the size of the list.
     *
     * @param lastShownList The list of cards to validate against.
     * @param index The index to be checked.
     * @return true if the index is invalid; false otherwise.
     */
    public static boolean isIndexInvalid(List<Card> lastShownList, Index index) {
        return index != Index.RANDOM
                && (index.getZeroBased() < 0 || index.getZeroBased() >= lastShownList.size());
    }


}
