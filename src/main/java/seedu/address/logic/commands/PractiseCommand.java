package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.Messages2;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Model2;
import seedu.address.model.person.Card;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class PractiseCommand extends Command2 {

    public static final String COMMAND_WORD = "practise";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": practise the card identified by the index number used in the displayed card list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_PRACTISE_CARD_SUCCESS = "Practise Card: %1$s";

    private final Index targetIndex;

    public PractiseCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model2 model) throws CommandException {
        requireNonNull(model);
        List<Card> lastShownList = model.getFilteredCardList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages2.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
        }
        Card cardToPractise = lastShownList.get(targetIndex.getZeroBased());

        return new CommandResult(
                String.format(Messages2.MESSAGE_CARDS_PRACTISE_VIEW, Messages2.format(cardToPractise)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PractiseCommand)) {
            return false;
        }

        PractiseCommand otherPractiseCommand = (PractiseCommand) other;
        return targetIndex.equals(otherPractiseCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}

