package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.card.Card;



/**
 * Practises a question using it's displayed index from the address book.
 */
public class SetDifficultyCommand extends Command {

    public static final String COMMAND_WORD = "set";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": set the card identified by the index number used in the displayed card list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Parameters: DIFFICULTY (must not be empty)\n"
            + "Example: " + COMMAND_WORD + " 1" + " d/ easy";

    public static final String MESSAGE_SET_DIFFICULTY_CARD_SUCCESS = "%1$s";

    private final Index targetIndex;

    private final String difficulty;

    /**
     * Constructs a {@code PractiseCommand} with the specified {@code targetIndex} and {@code difficulty}.
     *
     * @param targetIndex The index of the target to card.
     * @param difficulty The difficulty level for the card.
     */
    public SetDifficultyCommand(Index targetIndex, String difficulty) {
        this.targetIndex = targetIndex;
        this.difficulty = difficulty;
    }





    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Card> lastShownList = model.getFilteredCardList();
        int deckSize = lastShownList.size();
        String newDifficulty = this.difficulty.toLowerCase();

        if (targetIndex.getZeroBased() >= deckSize) {
            throw new CommandException(Messages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
        }
        Card cardToSetDifficulty = lastShownList.get(targetIndex.getZeroBased());

        if (Objects.equals(newDifficulty, "easy")) {
            cardToSetDifficulty.setDifficulty(newDifficulty);
            cardToSetDifficulty.setPriority(deckSize - 1);
            model.getDeck().sort();
            return new CommandResult(
                    String.format(Messages.MESSAGE_CARDS_SET_DIFFICULTY_VIEW_EASY,
                            Messages.formatSetDifficulty(cardToSetDifficulty, targetIndex)));
        }

        if (Objects.equals(newDifficulty, "medium")) {
            cardToSetDifficulty.setDifficulty(newDifficulty);
            cardToSetDifficulty.setPriority(Math.floorDiv(deckSize, 2));
            model.getDeck().sort();
            return new CommandResult(
                    String.format(Messages.MESSAGE_CARDS_SET_DIFFICULTY_VIEW_MEDIUM,
                            Messages.formatSetDifficulty(cardToSetDifficulty, targetIndex)));
        }

        if (Objects.equals(newDifficulty, "hard")) {
            cardToSetDifficulty.setDifficulty(newDifficulty);
            cardToSetDifficulty.setPriority(deckSize - Math.floorDiv(deckSize, 10));
            model.getDeck().sort();
            return new CommandResult(
                    String.format(Messages.MESSAGE_CARDS_SET_DIFFICULTY_VIEW_HARD,
                            Messages.formatSetDifficulty(cardToSetDifficulty, targetIndex)));
        } else {
            throw new CommandException(newDifficulty + Messages.MESSAGE_CARDS_SET_DIFFICULTY_VIEW_INVALID);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SetDifficultyCommand)) {
            return false;
        }

        SetDifficultyCommand otherSetDifficultyCommand = (SetDifficultyCommand) other;
        return targetIndex.equals(otherSetDifficultyCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }

}
