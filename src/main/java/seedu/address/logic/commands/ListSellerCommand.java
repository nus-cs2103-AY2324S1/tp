package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.displayable.seller.Seller;

/**
 * Displays information of a specified seller.
 */
public class ListSellerCommand extends Command {

    public static final String COMMAND_WORD = "slist";
    public static final String MESSAGE_LIST_SELLER_SUCCESS = "Got it. Here's the information of this seller:\n%1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays information of the seller identified by the index number used in the displayed seller list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;

    public ListSellerCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Seller> lastShownList = model.getFilteredSellerList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SELLER_DISPLAYED_INDEX);
        }

        Seller sellerToDisplay = lastShownList.get(targetIndex.getZeroBased());
        return new CommandResult(String.format(MESSAGE_LIST_SELLER_SUCCESS, Messages.format(sellerToDisplay)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListSellerCommand)) {
            return false;
        }

        ListSellerCommand otherListSellerCommand = (ListSellerCommand) other;
        return targetIndex.equals(otherListSellerCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
