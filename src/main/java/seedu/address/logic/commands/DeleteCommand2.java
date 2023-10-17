package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages2;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model2;
import seedu.address.model.person.Card;




/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand2 extends Command2 {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the card identified by the index number used in the displayed card list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_CARD_SUCCESS = "Deleted Card: %1$s";

    private final Index targetIndex;

    public DeleteCommand2(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model2 model) throws CommandException {
        requireNonNull(model);
        List<Card> lastShownList = model.getFilteredCardList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages2.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
        }

        Card cardToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteCard(cardToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_CARD_SUCCESS, Messages2.format(cardToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand2)) {
            return false;
        }

        DeleteCommand2 otherDeleteCommand = (DeleteCommand2) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
