package seedu.flashlingo.logic.commands;


import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.flashlingo.commons.core.index.Index;
import seedu.flashlingo.commons.util.ToStringBuilder;
import seedu.flashlingo.logic.Messages;
import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.model.flashcard.words.TranslatedWord;

/**
 * Flips to the other side of the flashcard.
 */
public class FlipCommand extends Command {

    public static final String COMMAND_WORD = "flip";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Flips the flashcard identified by the index number "
        + "used\nParameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Flashcard has been flipped!";
    private final Index targetIndex;
    /**
     * Creates an FlipCommand.
     */
    public FlipCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<FlashCard> lastShownList = model.getFilteredFlashCardList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }

        FlashCard toBeRevealed = lastShownList.get(targetIndex.getZeroBased());
        TranslatedWord translatedWord = model.reveal(toBeRevealed);
        return new CommandResult(MESSAGE_SUCCESS);
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FlipCommand)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
          .add("targetIndex", targetIndex)
          .toString();
    }
}
