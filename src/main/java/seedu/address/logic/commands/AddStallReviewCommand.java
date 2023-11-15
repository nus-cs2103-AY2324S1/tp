package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STALL;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.stall.Stall;
import seedu.address.model.stall.review.StallReview;

/**
 * Adds a review to a stall.
 */
public class AddStallReviewCommand extends Command {
    public static final String COMMAND_WORD = "review-stall";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a review to a stall "
            + "Parameters: "
            + PREFIX_STALL + "STALL_INDEX "
            + PREFIX_RATING + "STALL_RATING "
            + PREFIX_DESCRIPTION + "STALL_DESCRIPTION \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STALL
            + "1 "
            + PREFIX_RATING + "2 "
            + PREFIX_DESCRIPTION + "The auntie is pretty and ambience good.";
    public static final String MESSAGE_ADD_STALL_REVIEW_SUCCESS = "Stall review added: %1$s";
    public static final String MESSAGE_DUPLICATE_STALL_REVIEW = "This stall already has a review.";

    private final StallReview toAdd;
    private final Index index;

    /**
     * Creates an AddStallReviewCommand to add a review to the specified stall.
     *
     * @param stallReview The review to be added.
     * @param index       The index of the stall to which the review will be added.
     */
    public AddStallReviewCommand(StallReview stallReview, Index index) {
        requireNonNull(stallReview);
        this.toAdd = stallReview;
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Stall> lastShownList = model.getFilteredStallList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
        }
        Stall stallToAddReview = lastShownList.get(index.getZeroBased());

        if (stallToAddReview.hasStallReview()) {
            throw new CommandException(MESSAGE_DUPLICATE_STALL_REVIEW);
        }

        model.showStall(stallToAddReview);
        model.setFilteredStall(index);
        model.setFilteredItemList(index);

        stallToAddReview.setStallReview(toAdd);
        return new CommandResult(String.format(MESSAGE_ADD_STALL_REVIEW_SUCCESS, Messages.format(toAdd)),
                CommandResult.ViewType.STALL_DETAIL);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        AddStallReviewCommand that = (AddStallReviewCommand) other;
        return Objects.equals(toAdd, that.toAdd)
                && Objects.equals(index, that.index);
    }
}
