package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class GenderTableCommandResultTest {
    @Test
    public void equals() {
        Map<String, Long> titlesValuesMapping1 = new HashMap<>();
        titlesValuesMapping1.put("Male", 20L);
        titlesValuesMapping1.put("Female", 30L);
        Map<String, Long> titlesValuesMapping2 = new HashMap<>();
        titlesValuesMapping2.put("Male", 20L);
        titlesValuesMapping2.put("Female", 30L);
        Map<String, Long> titlesValuesMapping3 = new HashMap<>();
        titlesValuesMapping3.put("Male", 10L);
        titlesValuesMapping3.put("Female", 10L);
        CommandResult commandResult1 = new GenderTableCommandResult(titlesValuesMapping1);
        CommandResult commandResult2 = new GenderTableCommandResult(titlesValuesMapping2);
        CommandResult commandResult3 = new GenderTableCommandResult(titlesValuesMapping3);

        // same values -> returns true
        assertTrue(commandResult1.equals(commandResult2));

        // same object -> returns true
        assertTrue(commandResult1.equals(commandResult1));
        assertTrue(commandResult2.equals(commandResult2));

        // null -> returns false
        assertFalse(commandResult1.equals(null));

        // different types -> returns false
        assertFalse(commandResult1.equals(0.5f));

        // different count values -> returns false
        assertFalse(commandResult1.equals(commandResult3));
    }

    @Test
    public void hashcode() {
        Map<String, Long> titlesValuesMapping1 = new HashMap<>();
        titlesValuesMapping1.put("Male", 20L);
        titlesValuesMapping1.put("Female", 30L);
        Map<String, Long> titlesValuesMapping2 = new HashMap<>();
        titlesValuesMapping2.put("Male", 20L);
        titlesValuesMapping2.put("Female", 30L);
        Map<String, Long> titlesValuesMapping3 = new HashMap<>();
        titlesValuesMapping3.put("Male", 10L);
        titlesValuesMapping3.put("Female", 10L);
        CommandResult commandResult1 = new GenderTableCommandResult(titlesValuesMapping1);
        CommandResult commandResult2 = new GenderTableCommandResult(titlesValuesMapping2);
        CommandResult commandResult3 = new GenderTableCommandResult(titlesValuesMapping3);

        // same values -> returns same hashcode
        assertEquals(commandResult1.hashCode(), commandResult2.hashCode());

        // different count values -> returns different hashcode
        assertNotEquals(commandResult1.hashCode(), commandResult3.hashCode());

    }

    @Test
    public void toStringMethod() {
        Map<String, Long> titlesValuesMapping1 = new HashMap<>();
        titlesValuesMapping1.put("Male", 20L);
        titlesValuesMapping1.put("Female", 30L);
        GenderTableCommandResult commandResult = new GenderTableCommandResult(titlesValuesMapping1);
        String expected = GenderTableCommandResult.class.getCanonicalName() + "{feedbackToUser="
                + commandResult.getFeedbackToUser() + ", showHelp=" + commandResult.isShowHelp()
                + ", showTable=" + commandResult.isShowTable()
                + ", exit=" + commandResult.isExit() + "}";
        assertEquals(expected, commandResult.toString());
    }
}
