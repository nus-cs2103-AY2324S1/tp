package flashlingo.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.flashlingo.logic.Messages;
import seedu.flashlingo.logic.commands.NoCommand;
import seedu.flashlingo.logic.commands.ReviewCommand;
import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;
import seedu.flashlingo.model.UserPrefs;
import seedu.flashlingo.model.flashcard.WordOverduePredicate;

import static flashlingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static flashlingo.testutil.TypicalFlashCards.getTypicalFlashlingo;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Finds and lists all flashcards in flashlingo who is overdue.
 */
public class ReviewCommandTest {

    private Model model = new ModelManager(getTypicalFlashlingo(), new UserPrefs());
    @Test
    public void execute_review_success() {

        Model expectedModel = new ModelManager(new Flashlingo(model.getFlashlingo()), new UserPrefs());
        String expectedMessage = ReviewCommand.MESSAGE_SUCCESS + "\n" + "0 flashcards listed!" + "\n[]";
        assertCommandSuccess(new ReviewCommand(), model, expectedMessage, expectedModel);
    }
    @Test
    public void equals() {
        ReviewCommand reviewFirstCommand = new ReviewCommand();
        ReviewCommand reviewSecondCommand = new ReviewCommand();


        // same object -> returns true
        assertTrue(reviewFirstCommand.equals(reviewFirstCommand));

        // same values -> returns true
        ReviewCommand reviewFirstCommandCopy = new ReviewCommand();
        assertTrue(reviewFirstCommand.equals(reviewFirstCommandCopy));

        // different types -> returns false
        assertFalse(reviewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(reviewFirstCommand.equals(null));

//        // different FlashCard -> returns false
//        assertFalse(reviewFirstCommand.equals(reviewSecondCommand));
    }

//    public static final String COMMAND_WORD = "review";
//
//    public static final String MESSAGE_SUCCESS = "Listed all flashcards you need to review";
//
//    private final WordOverduePredicate predicate;
//
//    public ReviewCommandTest() {
//        this.predicate = new WordOverduePredicate();
//    }
//    @Override
//    public CommandResultTest execute(Model model) {
//        requireNonNull(model);
//        model.updateFilteredFlashCardList(predicate);
//        return new CommandResultTest(MESSAGE_SUCCESS + "\n"
//                + String.format(Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW + "\n"
//                                + model.getFilteredFlashCardList(),
//                        model.getFilteredFlashCardList().size()));
//    }
}
