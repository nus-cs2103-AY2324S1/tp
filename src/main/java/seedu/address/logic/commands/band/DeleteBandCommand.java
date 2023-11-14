package seedu.address.logic.commands.band;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.band.Band;

/**
 * Deletes a band identified using it's displayed index from the address book.
 */
public class DeleteBandCommand extends Command {

    public static final String COMMAND_WORD = "deleteb";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the band identified by the index number used in the displayed band list.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_BAND_SUCCESS = "Deleted Band: %1$s";

    private final Index targetIndex;

    /**
     * Creates a {@code DeleteBandCommand} to delete the specified {@code Band}.
     */
    public DeleteBandCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Band> lastShownList = model.getFilteredBandList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BAND_DISPLAYED_INDEX);
        }

        Band bandToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteBand(bandToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_BAND_SUCCESS, Messages.format(bandToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteBandCommand)) {
            return false;
        }

        DeleteBandCommand otherDeleteBandCommand = (DeleteBandCommand) other;
        return targetIndex.equals(otherDeleteBandCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
