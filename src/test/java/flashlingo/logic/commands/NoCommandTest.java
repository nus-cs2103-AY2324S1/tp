package flashlingo.logic.commands;


import org.junit.jupiter.api.Test;

import seedu.flashlingo.logic.commands.NoCommand;
import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;
import seedu.flashlingo.model.UserPrefs;
import seedu.flashlingo.model.flashcard.FlashCard;

import static flashlingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static flashlingo.testutil.Assert.assertThrows;
import static flashlingo.testutil.TypicalFlashCards.getTypicalFlashlingo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Indicates user has not yet memorized the word.
 */
public class NoCommandTest {
    private Model model = new ModelManager(getTypicalFlashlingo(), new UserPrefs());
    @Test
    public void execute_no_success() {

        Model expectedModel = new ModelManager(new Flashlingo(model.getFlashlingo()), new UserPrefs());

        assertThrows(CommandException.class, () -> new NoCommand().execute(model));

    }

//    public static final String COMMAND_WORD = "no";
//
//    // For help function
//    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Indicates user hasn't memorized the word.\n"
//        + "Example: " + COMMAND_WORD + " ";
//
//    public static final String MESSAGE_SUCCESS = "It seems like that you did not memorize this word well.";
//
//
//    /**
//     * Creates an NoCommandTest.
//     */
//    public NoCommandTest() {};

    @Test
    public void equals() {
        NoCommand noFirstCommand = new NoCommand();
        NoCommand noSecondCommand = new NoCommand();


        // same object -> returns true
        assertTrue(noFirstCommand.equals(noFirstCommand));

        // same values -> returns true
        NoCommand noFirstCommandCopy = new NoCommand();
        assertTrue(noFirstCommand.equals(noFirstCommandCopy));

        // different types -> returns false
        assertFalse(noFirstCommand.equals(1));

        // null -> returns false
        assertFalse(noFirstCommand.equals(null));

    }
//    @Override
//    public CommandResultTest execute(Model model) throws CommandExceptionTest {
//        requireNonNull(model);
//        model.rememberWord(false);
//        String response = model.nextReviewWord();
//        return new CommandResultTest(MESSAGE_SUCCESS + "\n" + response);
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
