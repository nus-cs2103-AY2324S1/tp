package flashlingo.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.flashlingo.commons.util.ToStringBuilder;
import seedu.flashlingo.logic.commands.RevealCommand;
import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;
import seedu.flashlingo.model.UserPrefs;
import seedu.flashlingo.model.flashcard.FlashCard;

import static flashlingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static flashlingo.testutil.TypicalFlashCards.getTypicalFlashlingo;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Indicates user has not yet memorized the word.
 */
public class RevealCommandTest {
    private Model model = new ModelManager(getTypicalFlashlingo(), new UserPrefs());
    @Test
    public void execute_reveal_success() {
        Model expectedModel = new ModelManager(new Flashlingo(model.getFlashlingo()), new UserPrefs());
        FlashCard currentCard = model.getFilteredFlashCardList().get(0);
        assertCommandSuccess(new RevealCommand(), model
                , RevealCommand.MESSAGE_SUCCESS + currentCard.getTranslatedWord(), expectedModel);
    }
    @Test
    public void equals() {
        RevealCommand revealFirstCommand = new RevealCommand();
        RevealCommand revealSecondCommand = new RevealCommand();


        // same object -> returns true
        assertTrue(revealFirstCommand.equals(revealFirstCommand));

        // same values -> returns true
        RevealCommand revealFirstCommandCopy = new RevealCommand();
        assertTrue(revealFirstCommand.equals(revealFirstCommandCopy));

        // different types -> returns false
        assertFalse(revealFirstCommand.equals(1));

        // null -> returns false
        assertFalse(revealFirstCommand.equals(null));

    }
//    public static final String COMMAND_WORD = "reveal";
//
//    // For help function
//    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Reveals the translation.\n"
//        + "Example: " + COMMAND_WORD + " ";
//
//    public static final String MESSAGE_SUCCESS = "The translation is: ";
//
//
//    /**
//     * Creates an NoCommandTest.
//     */
//    public RevealCommandTest() {};
//
//    @Override
//    public CommandResultTest execute(Model model) throws CommandExceptionTest {
//        requireNonNull(model);
//        FlashCard currentCard = model.getFilteredFlashCardList().get(0);
//        return new CommandResultTest(MESSAGE_SUCCESS + currentCard.getTranslatedWord());
//    }
//    @Override
//    public boolean equals(Object other) {
//        if (other == this) {
//            return true;
//        }
//
//        // instanceof handles nulls
//        if (!(other instanceof AddCommandTest)) {
//            return false;
//        }
//
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this)
//          .toString();
//    }
}
