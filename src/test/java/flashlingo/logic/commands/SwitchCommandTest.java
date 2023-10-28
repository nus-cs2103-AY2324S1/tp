package flashlingo.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.flashlingo.commons.util.ToStringBuilder;
import seedu.flashlingo.logic.commands.CommandResult;
import seedu.flashlingo.logic.commands.DeleteCommand;
import seedu.flashlingo.logic.commands.HelpCommand;
import seedu.flashlingo.logic.commands.SwitchCommand;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;

import static flashlingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static flashlingo.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashlingo.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

/**
 * Switches the color theme of the application.
 */
public class SwitchCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_switch_success() {
        CommandResult expectedCommandResult = new CommandResult(SwitchCommand.MESSAGE_SUCCESS, false, false,
                true);
        assertCommandSuccess(new SwitchCommand(), model, expectedCommandResult, expectedModel);
    }
    @Test
    public void equals() {
        SwitchCommand switchFirstCommand = new SwitchCommand();
        SwitchCommand switchSecondCommand = new SwitchCommand();


        // same object -> returns true
        assertTrue(switchFirstCommand.equals(switchFirstCommand));

        // same values -> returns true
        SwitchCommand switchFirstCommandCopy = new SwitchCommand();
        assertTrue(switchFirstCommand.equals(switchFirstCommandCopy));

        // different types -> returns false
        assertFalse(switchFirstCommand.equals(1));

        // null -> returns false
        assertFalse(switchFirstCommand.equals(null));

//        // different FlashCard -> returns false
//        assertFalse(switchFirstCommand.equals(switchSecondCommand));
    }
//    public static final String COMMAND_WORD = "switch";
//    public static final String MESSAGE_SUCCESS = "You have switched to ";
//
//    public SwitchCommandTest() {}
//
//    @Override
//    public CommandResultTest execute(Model model) throws CommandExceptionTest {
//        requireNonNull(model);
//        return new CommandResultTest(MESSAGE_SUCCESS, false, false, true);
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        if (other == this) {
//            return true;
//        }
//
//        // instanceof handles nulls
//        if (!(other instanceof SwitchCommandTest)) {
//            return false;
//        }
//
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this)
//                .toString();
//    }
}
