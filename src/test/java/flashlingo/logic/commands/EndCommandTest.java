package flashlingo.logic.commands;


import org.junit.jupiter.api.Test;
import seedu.flashlingo.commons.util.ToStringBuilder;
import seedu.flashlingo.logic.commands.NoCommand;
import seedu.flashlingo.logic.commands.EndCommand;
import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;
import seedu.flashlingo.model.UserPrefs;

import static flashlingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static flashlingo.testutil.TypicalFlashCards.getTypicalFlashlingo;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Ends a new session of reviewing.
 */
public class EndCommandTest {
    private Model model = new ModelManager(getTypicalFlashlingo(), new UserPrefs());
    @Test
    public void execute_end_success() {

        Model expectedModel = new ModelManager(new Flashlingo(model.getFlashlingo()), new UserPrefs());

        assertCommandSuccess(new EndCommand(), model, EndCommand.MESSAGE_SUCCESS, expectedModel);
    }
    @Test
    public void equals() {
        EndCommand endFirstCommand = new EndCommand();
        EndCommand endSecondCommand = new EndCommand();


        // same object -> returns true
        assertTrue(endFirstCommand.equals(endFirstCommand));

        // same values -> returns true
        EndCommand endFirstCommandCopy = new EndCommand();
        assertTrue(endFirstCommand.equals(endFirstCommandCopy));

        // different types -> returns false
        assertFalse(endFirstCommand.equals(1));

        // null -> returns false
        assertFalse(endFirstCommand.equals(null));

    }

//    public static final String COMMAND_WORD = "End";
//
//    // For help function
//    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Ends reviewing session.\n"
//        + "Example: " + COMMAND_WORD + " ";
//
//    public static final String MESSAGE_SUCCESS = "Review Session has been Ended.";
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
//          .add("End", "")
//          .toString();
//    }
}
