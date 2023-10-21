package seedu.flashlingo.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.flashlingo.commons.util.ToStringBuilder;
import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.model.flashcard.NextReviewWordPredicate;
import seedu.flashlingo.model.flashcard.WordOverduePredicate;

/**
 * Starts a new session of reviewing.
 */
public class StartCommand extends Command {

    public static final String COMMAND_WORD = "start";

    // For help function
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Starts reviewing session.\n"
        + "Example: " + COMMAND_WORD + " ";

    public static final String MESSAGE_SUCCESS = "Review Session has been started.";
    public static final String MESSAGE_STATE_REPEATED = "Already at review session";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.isReviewSession()) {
            throw new CommandException(MESSAGE_STATE_REPEATED);
        }
        model.updateFilteredFlashCardList(new WordOverduePredicate());
        FlashCard toBeReviewed = model.getFilteredFlashCardList().get(0);
        Predicate<FlashCard> t = new NextReviewWordPredicate(toBeReviewed);
        model.updateFilteredFlashCardList(t);
        model.updateReviewSessionState();
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
          .add("start", "")
          .toString();
    }
}
