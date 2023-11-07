package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.card.Card;
import seedu.address.model.exceptions.RandomIndexNotInitialisedException;

/**
 * Solves a question using it's displayed index from the Deck.
 */
public class SolveCommand extends Command {

    public static final String COMMAND_WORD = "solve";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": solve the card identified by its index in the displayed card list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SOLVE_CARD_SUCCESS = "%1$s";

    /** Specific {@code Index} in Deck to solve */
    private final Index targetIndex;

    /**
     * Constructs a {@code PractiseCommand} with the specified {@code targetIndex}
     *
     * @param targetIndex The index of the target to card.
     */
    public SolveCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Card> lastShownList = model.getFilteredCardList();

        Index actualIndex = resolveIndex(model, targetIndex);

        if (isIndexInvalid(lastShownList, targetIndex)) {
            throw new CommandException(Messages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
        }

        Card cardToSolve = lastShownList.get(actualIndex.getZeroBased());
        updateModel(model, cardToSolve);

        return new CommandResult(String.format(Messages.MESSAGE_CARDS_SOLVE_VIEW,
                        Messages.formatSolve(cardToSolve, actualIndex)));
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

        // compare Index equality
        SolveCommand otherSolveCommand = (SolveCommand) other;
        return targetIndex.equals(otherSolveCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }

    private Index resolveIndex(Model model, Index targetIndex) throws CommandException {
        if (targetIndex.equals(Index.RANDOM)) {
            try {
                return model.getRandomIndex();
            } catch (RandomIndexNotInitialisedException e) {
                throw new CommandException(Messages.MESSAGE_RANDOM_INDEX_NOT_INITIALISED);
            }
        } else {
            return targetIndex;
        }
    }

    private void updateModel(Model model, Card card) {
        card.incrementSolveCount();
        model.setCard(card, card); // This is assuming setCard method does the necessary UI updates and model changes.
        model.getGoal().solvedCard();
    }
}
