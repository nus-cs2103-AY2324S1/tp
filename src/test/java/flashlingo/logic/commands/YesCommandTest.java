package flashlingo.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.flashlingo.commons.util.ToStringBuilder;
import seedu.flashlingo.logic.commands.NoCommand;
import seedu.flashlingo.logic.commands.YesCommand;
import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;
import seedu.flashlingo.model.UserPrefs;

import static flashlingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static flashlingo.testutil.Assert.assertThrows;
import static flashlingo.testutil.TypicalFlashCards.getTypicalFlashlingo;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Indicates user has memorized the word.
 */
public class YesCommandTest {

    private Model model = new ModelManager(getTypicalFlashlingo(), new UserPrefs());
    @Test
    public void execute_yes_success() {

        Model expectedModel = new ModelManager(new Flashlingo(model.getFlashlingo()), new UserPrefs());

        assertThrows(CommandException.class, () -> new YesCommand().execute(model));
    }
    @Test
    public void equals() {
        YesCommand yesFirstCommand = new YesCommand();
        YesCommand yesSecondCommand = new YesCommand();


        // same object -> returns true
        assertTrue(yesFirstCommand.equals(yesFirstCommand));

        // same values -> returns true
        YesCommand yesFirstCommandCopy = new YesCommand();
        assertTrue(yesFirstCommand.equals(yesFirstCommandCopy));

        // different types -> returns false
        assertFalse(yesFirstCommand.equals(1));

        // null -> returns false
        assertFalse(yesFirstCommand.equals(null));

//        // different FlashCard -> returns false
//        assertFalse(yesFirstCommand.equals(yesSecondCommand));
    }

//    public static final String COMMAND_WORD = "yes";
//
//    // For help function
//    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Indicates user has successfully memorized the word.\n"
//        + "Example: " + COMMAND_WORD + " ";
//
//    public static final String MESSAGE_SUCCESS = "Great Job! You have indicated that you have memorized the word!";
//    /**
//     * Creates an YesCommandTest.
//     */
//    public YesCommandTest() {}
//
//    @Override
//    public CommandResultTest execute(Model model) throws CommandExceptionTest {
//        requireNonNull(model);
//        model.rememberWord(true);
//        String response = model.nextReviewWord();
//        return new CommandResultTest(MESSAGE_SUCCESS + "\n" + response);
//    }
//
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
