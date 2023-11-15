package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void isListToday_success() {
        CommandResult commandResult = new CommandResult("feedback", ListTodayCommand.COMMAND_WORD);
        assertTrue(commandResult.isListToday());
    }

    @Test
    public void isListToday_fail() {
        CommandResult commandResult = new CommandResult("feedback", AddCommand.COMMAND_WORD);
        assertFalse(commandResult.isListToday());
    }

    @Test
    public void isListCompleted_success() {
        CommandResult commandResult = new CommandResult("feedback", ListCompletedCommand.COMMAND_WORD);
        assertTrue(commandResult.isListCompleted());
    }

    @Test
    public void isListCompleted_fail() {
        CommandResult commandResult = new CommandResult("feedback", HelpCommand.COMMAND_WORD);
        assertFalse(commandResult.isListCompleted());
    }

    @Test
    public void affectsReminders_addCommand_true() {
        CommandResult commandResult = new CommandResult("feedback", AddCommand.COMMAND_WORD);
        assertTrue(commandResult.affectsReminders());
    }

    @Test
    public void affectsReminders_takeCommand_true() {
        CommandResult commandResult = new CommandResult("feedback", TakeCommand.COMMAND_WORD);
        assertTrue(commandResult.affectsReminders());
    }

    @Test
    public void affectsReminders_untakeCommand_true() {
        CommandResult commandResult = new CommandResult("feedback", UntakeCommand.COMMAND_WORD);
        assertTrue(commandResult.affectsReminders());
    }

    @Test
    public void affectsReminders_editCommand_true() {
        CommandResult commandResult = new CommandResult("feedback", EditCommand.COMMAND_WORD);
        assertTrue(commandResult.affectsReminders());
    }

    @Test
    public void affectsReminders_invalidCommand_false() {
        CommandResult commandResult = new CommandResult("feedback", ListCommand.COMMAND_WORD);
        assertFalse(commandResult.affectsReminders());
    }

    @Test
    public void isShowHelp_success() {
        CommandResult commandResult = new CommandResult("feedback", HelpCommand.COMMAND_WORD);
        assertTrue(commandResult.isShowHelp());
    }

    @Test
    public void isShowHelp_fail() {
        CommandResult commandResult = new CommandResult("feedback", ListCommand.COMMAND_WORD);
        assertFalse(commandResult.isShowHelp());
    }

    @Test
    public void isExit_success() {
        CommandResult commandResult = new CommandResult("feedback", ExitCommand.COMMAND_WORD);
        assertTrue(commandResult.isExit());
    }

    @Test
    public void isExit_fail() {
        CommandResult commandResult = new CommandResult("feedback", ListCompletedCommand.COMMAND_WORD);
        assertFalse(commandResult.isExit());
    }

    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback")));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", HelpCommand.COMMAND_WORD)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", ExitCommand.COMMAND_WORD)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", HelpCommand.COMMAND_WORD).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", ExitCommand.COMMAND_WORD).hashCode());
    }

    @Test
    public void toStringMethod() {
        CommandResult commandResult = new CommandResult("feedback");
        String expected = CommandResult.class.getCanonicalName() + "{feedbackToUser="
                + commandResult.getFeedbackToUser() + ", commandWord=" + commandResult.getCommandWord() + "}";
        assertEquals(expected, commandResult.toString());
    }
}
