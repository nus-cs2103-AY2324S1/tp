package seedu.address.logic.commands;


import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.displayable.buyer.Buyer;

/**
 * Deletes a buyer identified using its displayed index from the address book.
 */
public class DeleteBuyerCommand extends Command {

    public static final String COMMAND_WORD = "bdelete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the buyer identified by the index number used in the displayed buyer list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_BUYER_SUCCESS = "Got it. I’ve deleted a buyer contact:\n%1$s";

    private final Index targetIndex;

    public DeleteBuyerCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Buyer> lastShownList = model.getFilteredBuyerList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BUYER_DISPLAYED_INDEX);
        }

        Buyer buyerToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteBuyer(buyerToDelete);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_DELETE_BUYER_SUCCESS, Messages.format(buyerToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteBuyerCommand)) {
            return false;
        }

        DeleteBuyerCommand otherDeleteBuyerCommand = (DeleteBuyerCommand) other;
        return targetIndex.equals(otherDeleteBuyerCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
