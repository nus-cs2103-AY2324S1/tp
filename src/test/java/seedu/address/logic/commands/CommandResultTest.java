package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    void isChangeTab_testIsChangeTab_true() {
        CommandResult changeToContacts = new CommandResult("", false, 0, false);
        CommandResult changeToFinance = new CommandResult("", false, 1, false);
        CommandResult changeToEvents = new CommandResult("", false, 2, false);
        assertTrue(changeToContacts.isChangeTab());
        assertTrue(changeToFinance.isChangeTab());
        assertTrue(changeToEvents.isChangeTab());
    }

    @Test
    void isChangeTab_testIsChangeTab_false() {
        CommandResult notChangeTab = new CommandResult("", false, null, false);
        assertFalse(notChangeTab.isChangeTab());
    }

    @Test
    void getTabToChange_testReturnRightTabIndex_success() {
        CommandResult tabChange = new CommandResult("", false, 2, false);
        assertEquals(2, tabChange.getTabToChange());
    }

    @Test
    void getTabToChange_testNullInput_throwNullPointerException() {
        CommandResult notTabChange = new CommandResult("", false, null, false);
        assertThrows(NullPointerException.class, notTabChange::getTabToChange);
    }

    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, null, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, null, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, null, true)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, null, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, null, true).hashCode());
    }

    @Test
    public void toStringMethod() {
        CommandResult commandResult = new CommandResult("feedback");
        String expected = CommandResult.class.getCanonicalName() + "{feedbackToUser="
                + commandResult.getFeedbackToUser() + ", showHelp=" + commandResult.isShowHelp()
                + ", exit=" + commandResult.isExit() + "}";
        assertEquals(expected, commandResult.toString());
    }
}
