package seedu.flashlingo.logic.commands;


import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.flashlingo.commons.core.index.Index;
import seedu.flashlingo.commons.util.ToStringBuilder;
import seedu.flashlingo.logic.Messages;
import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.flashcard.FlashCard;

/**
 * Indicates user has not yet memorized the word.
 */
public class RevealCommand extends Command {

    public static final String COMMAND_WORD = "reveal";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Reveals the word identified by the index number used "
        + "\nParameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "The translation is: ";
    private final Index targetIndex;
    /**
     * Creates an RevealCommand.
     */
    public RevealCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<FlashCard> lastShownList = model.getFilteredFlashCardList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }

        FlashCard flashCard = lastShownList.get(targetIndex.getZeroBased());
        return new CommandResult(MESSAGE_SUCCESS + flashCard.getTranslatedWord().getWord());
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RevealCommand)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
