package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STALL;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.stall.Stall;

/**
 * Deletes a stall review identified using it's displayed index from FoodNotes.
 */
public class DeleteStallReviewCommand extends Command {

    public static final String COMMAND_WORD = "delete-stall-review";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the stall review identified by the index number used in the displayed stall list.\n"
            + "Parameters: "
            + PREFIX_STALL + "STALL_INDEX \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STALL + "1";

    public static final String MESSAGE_DELETE_STALL_REVIEW_SUCCESS = "Deleted Stall Review: %1$s";
    private static final String MESSAGE_ITEM_REVIEW_NOT_FOUND = "This stall does not have a review.";

    private final Index targetIndex;

    public DeleteStallReviewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Stall> lastShownList = model.getFilteredStallList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
        }

        Stall stallToDeleteReview = lastShownList.get(targetIndex.getZeroBased());

        if (!stallToDeleteReview.hasStallReview()) {
            throw new CommandException(MESSAGE_ITEM_REVIEW_NOT_FOUND);
        }

        stallToDeleteReview.deleteReview();

        model.showStall(stallToDeleteReview);
        model.setFilteredStall(targetIndex);
        model.setFilteredItemList(targetIndex);

        return new CommandResult(String.format(MESSAGE_DELETE_STALL_REVIEW_SUCCESS,
                Messages.format(stallToDeleteReview)), CommandResult.ViewType.STALL_DETAIL);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        DeleteStallReviewCommand that = (DeleteStallReviewCommand) other;
        return Objects.equals(targetIndex, that.targetIndex);
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
