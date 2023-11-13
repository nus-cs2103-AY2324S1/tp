package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.card.Card;

/**
 * Displays the hint of a question using it's displayed index from the Deck.
 */
public class HintCommand extends Command {

    public static final String COMMAND_WORD = "hint";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": displays the hint for a question identified by its index in the displayed card list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_HINT_CARD_SUCCESS = "%1$s";

    /** Specific {@code Index} in Deck to display hint for */
    private final Index targetIndex;

    /**
     * Constructs a {@code PractiseCommand} with the specified {@code targetIndex}
     *
     * @param targetIndex The index of the target to card.
     */
    public HintCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Card> lastShownList = model.getFilteredCardList();

        if (isIndexInvalid(lastShownList, targetIndex)) {
            throw new CommandException(Messages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
        }

        Card cardToHint = lastShownList.get(targetIndex.getZeroBased());

        return new CommandResult(String.format(Messages.MESSAGE_CARDS_HINT_VIEW,
                Messages.formatHint(cardToHint, targetIndex)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof HintCommand)) {
            return false;
        }

        // compare Index equality
        HintCommand otherHintCommand = (HintCommand) other;
        return targetIndex.equals(otherHintCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
