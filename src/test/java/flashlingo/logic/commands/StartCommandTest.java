package flashlingo.logic.commands;


import org.junit.jupiter.api.Test;
import seedu.flashlingo.commons.util.ToStringBuilder;
import seedu.flashlingo.logic.commands.NoCommand;
import seedu.flashlingo.logic.commands.StartCommand;
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
 * Starts a new session of reviewing.
 */
public class StartCommandTest {
    private Model model = new ModelManager(getTypicalFlashlingo(), new UserPrefs());
    @Test
    public void execute_start_success() {

        Model expectedModel = new ModelManager(new Flashlingo(model.getFlashlingo()), new UserPrefs());

        assertThrows(CommandException.class, () -> new StartCommand().execute(model));
    }
    @Test
    public void equals() {
        StartCommand startFirstCommand = new StartCommand();
        StartCommand startSecondCommand = new StartCommand();


        // same object -> returns true
        assertTrue(startFirstCommand.equals(startFirstCommand));

        // same values -> returns true
        StartCommand startFirstCommandCopy = new StartCommand();
        assertTrue(startFirstCommand.equals(startFirstCommandCopy));

        // different types -> returns false
        assertFalse(startFirstCommand.equals(1));

        // null -> returns false
        assertFalse(startFirstCommand.equals(null));

//        // different FlashCard -> returns false
//        assertFalse(startFirstCommand.equals(startSecondCommand));
    }

//    public static final String COMMAND_WORD = "start";
//
//    // For help function
//    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Starts reviewing session.\n"
//        + "Example: " + COMMAND_WORD + " ";
//
//    public static final String MESSAGE_SUCCESS = "Review Session has been started.";
//    @Override
//    public CommandResultTest execute(Model model) throws CommandExceptionTest {
//        requireNonNull(model);
//        model.nextReviewWord();
//        return new CommandResultTest(String.format(MESSAGE_SUCCESS));
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
//          .add("start", "")
//          .toString();
//    }
}
