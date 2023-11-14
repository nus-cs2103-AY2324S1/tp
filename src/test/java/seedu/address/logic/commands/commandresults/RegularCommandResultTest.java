package seedu.address.logic.commands.commandresults;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RegularCommandResultTest {

    @Test
    public void displayableCommandResultTest() {
        RegularCommandResult commandResult = new RegularCommandResult("feedback");

        assertFalse(commandResult.isDisplayableCommandResult());
    }

    @Test
    public void equals() {
        RegularCommandResult commandResult = new RegularCommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new RegularCommandResult("feedback")));
        assertTrue(commandResult.equals(new RegularCommandResult("feedback", false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new RegularCommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new RegularCommandResult("feedback", true, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new RegularCommandResult("feedback", false, true)));
    }

    @Test
    public void hashcode() {
        RegularCommandResult commandResult = new RegularCommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new RegularCommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new RegularCommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new RegularCommandResult("feedback", true, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new RegularCommandResult("feedback", false, true).hashCode());
    }

    @Test
    public void toStringMethod() {
        RegularCommandResult commandResult = new RegularCommandResult("feedback");
        String expected = RegularCommandResult.class.getCanonicalName() + "{feedbackToUser="
                + commandResult.getFeedbackToUser() + ", showHelp=" + commandResult.isShowHelp()
                + ", exit=" + commandResult.isExit() + "}";
        assertEquals(expected, commandResult.toString());
    }
}
