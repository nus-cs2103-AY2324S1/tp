package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.card.Card;
import seedu.address.model.card.Difficulty;
import seedu.address.model.exceptions.RandomIndexNotInitialisedException;

/**
 * Set the difficulty of a Card using its displayed index from the Deck.
 */
public class SetDifficultyCommand extends Command {

    public static final String COMMAND_WORD = "set";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": set the card identified by the index number used in the displayed card list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Parameters: DIFFICULTY (must not be empty)\n"
            + "Example: " + COMMAND_WORD + " 1" + " d/ easy";

    public static final String MESSAGE_SET_DIFFICULTY_CARD_SUCCESS = "%1$s";

    /** Specific {@code Index} in Deck to set difficulty */
    private final Index targetIndex;

    /** Difficulty of {@code Card} to set to */
    private final String difficulty;

    /**
     * Constructs a {@code PractiseCommand} with the specified {@code targetIndex} and {@code difficulty}.
     *
     * @param targetIndex The index of the target Card.
     * @param difficulty The difficulty level for the Card.
     */
    public SetDifficultyCommand(Index targetIndex, String difficulty) {
        assert targetIndex != null : "Index cannot be null";
        assert difficulty != null && !difficulty.trim().isEmpty() : "Difficulty cannot be null or empty";

        this.targetIndex = targetIndex;
        this.difficulty = difficulty;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Card> lastShownList = model.getFilteredCardList();

        Index actualIndex = getActualIndex(model, targetIndex);

        if (isIndexInvalid(lastShownList, targetIndex)) {
            throw new CommandException(Messages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
        }

        Card cardToSetDifficulty = lastShownList.get(actualIndex.getZeroBased());
        Difficulty difficultySet = getDifficultyFromString(difficulty);
        return updateCardBasedOnDifficulty(model, cardToSetDifficulty, difficultySet, actualIndex);
    }

    private CommandResult updatePracticeDate(Model model, Difficulty difficulty,
                                             Card card, String message, Index targetIndex) {
        assert(model != null);
        assert(difficulty != null);
        assert(!message.isEmpty());

        card.setDifficulty(difficulty);
        card.setNewPracticeDateWith(difficulty);
        model.getDeck().sort();
        return new CommandResult(
                String.format(message, Messages.formatSetDifficulty(card, targetIndex)));
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

        // compare Index equality
        SetDifficultyCommand otherSetDifficultyCommand = (SetDifficultyCommand) other;
        return targetIndex.equals(otherSetDifficultyCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }

    private Difficulty getDifficultyFromString(String difficultyString) throws CommandException {
        try {
            return Difficulty.valueOf(difficultyString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CommandException(difficultyString + Messages.MESSAGE_CARDS_SET_DIFFICULTY_VIEW_INVALID);
        }
    }

    private CommandResult updateCardBasedOnDifficulty(Model model, Card card,
                                                      Difficulty difficulty, Index index) throws CommandException {
        String message;
        switch (difficulty) {
        case EASY:
            message = Messages.MESSAGE_CARDS_SET_DIFFICULTY_VIEW_EASY;
            break;
        case MEDIUM:
            message = Messages.MESSAGE_CARDS_SET_DIFFICULTY_VIEW_MEDIUM;
            break;
        case HARD:
            message = Messages.MESSAGE_CARDS_SET_DIFFICULTY_VIEW_HARD;
            break;
        default:
            throw new CommandException("Invalid difficulty level: " + this.difficulty);
        }
        return updatePracticeDate(model, difficulty, card, message, index);
    }

    private Index getActualIndex(Model model, Index targetIndex) throws CommandException {
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

}
