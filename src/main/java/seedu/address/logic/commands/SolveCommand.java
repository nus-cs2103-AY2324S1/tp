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
 * Practises a question using it's displayed index from the address book.
 */
public class SolveCommand extends Command {

    public static final String COMMAND_WORD = "solve";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": solve the card identified by the index number used in the displayed card list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SOLVE_CARD_SUCCESS = "%1$s";

    private final Index targetIndex;


    /**
     * Constructs a {@code PractiseCommand} with the specified {@code targetIndex} and {@code difficulty}.
     *
     * @param targetIndex The index of the target to card.
     */
    public SolveCommand(Index targetIndex) {
        this.targetIndex = targetIndex;

    }





    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Card> lastShownList = model.getFilteredCardList();
        int deckSize = lastShownList.size();

        if (targetIndex.getZeroBased() >= deckSize) {
            throw new CommandException(Messages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
        }
        Card cardToSolve = lastShownList.get(targetIndex.getZeroBased());

        return new CommandResult(
                String.format(Messages.MESSAGE_CARDS_SOLVE_VIEW,
                        Messages.formatSolve(cardToSolve, targetIndex)));

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SolveCommand)) {
            return false;
        }

        SolveCommand otherSolveCommand = (SolveCommand) other;
        return targetIndex.equals(otherSolveCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }

}
