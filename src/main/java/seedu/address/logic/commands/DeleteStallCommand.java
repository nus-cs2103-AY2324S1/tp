package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STALL;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.stall.Stall;

/**
 * Deletes a stall identified using it's displayed index from FoodNotes.
 */
public class DeleteStallCommand extends Command {

    public static final String COMMAND_WORD = "delete-stall";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the stall identified by the index number used in the displayed stall list.\n"
            + "Parameters: "
            + PREFIX_STALL + "STALL_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STALL + "1";

    public static final String MESSAGE_DELETE_STALL_SUCCESS = "Deleted Stall: %1$s";

    private final Index targetIndex;

    public DeleteStallCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Stall> lastShownList = model.getFilteredStallList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
        }

        Stall stallToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteStall(stallToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_STALL_SUCCESS, Messages.format(stallToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteStallCommand)) {
            return false;
        }

        DeleteStallCommand otherDeleteStallCommand = (DeleteStallCommand) other;
        return targetIndex.equals(otherDeleteStallCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
