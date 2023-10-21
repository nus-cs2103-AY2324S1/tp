package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class SecLevelTableCommandResultTest {
    @Test
    public void equals() {
        Map<String, Long> titlesValuesMapping1 = new HashMap<>();
        titlesValuesMapping1.put("Sec 1", 20L);
        titlesValuesMapping1.put("Sec 2", 30L);
        titlesValuesMapping1.put("Sec 3", 40L);
        titlesValuesMapping1.put("Sec 4", 50L);
        Map<String, Long> titlesValuesMapping2 = new HashMap<>();
        titlesValuesMapping2.put("Sec 1", 20L);
        titlesValuesMapping2.put("Sec 2", 30L);
        titlesValuesMapping2.put("Sec 3", 40L);
        titlesValuesMapping2.put("Sec 4", 50L);
        Map<String, Long> titlesValuesMapping3 = new HashMap<>();
        titlesValuesMapping3.put("Sec 1", 10L);
        titlesValuesMapping3.put("Sec 2", 10L);
        titlesValuesMapping3.put("Sec 3", 20L);
        titlesValuesMapping3.put("Sec 4", 30L);
        CommandResult commandResult1 = new SecLevelTableCommandResult(titlesValuesMapping1);
        CommandResult commandResult2 = new SecLevelTableCommandResult(titlesValuesMapping2);
        CommandResult commandResult3 = new SecLevelTableCommandResult(titlesValuesMapping3);

        // same values -> returns true
        assertTrue(commandResult1.equals(commandResult2));

        // same object -> returns true
        assertTrue(commandResult1.equals(commandResult1));
        assertTrue(commandResult2.equals(commandResult2));
        assertTrue(commandResult3.equals(commandResult3));


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
        titlesValuesMapping1.put("Sec 1", 20L);
        titlesValuesMapping1.put("Sec 2", 30L);
        titlesValuesMapping1.put("Sec 3", 40L);
        titlesValuesMapping1.put("Sec 4", 50L);
        Map<String, Long> titlesValuesMapping2 = new HashMap<>();
        titlesValuesMapping2.put("Sec 1", 20L);
        titlesValuesMapping2.put("Sec 2", 30L);
        titlesValuesMapping2.put("Sec 3", 40L);
        titlesValuesMapping2.put("Sec 4", 50L);
        Map<String, Long> titlesValuesMapping3 = new HashMap<>();
        titlesValuesMapping3.put("Sec 1", 10L);
        titlesValuesMapping3.put("Sec 2", 10L);
        titlesValuesMapping3.put("Sec 3", 20L);
        titlesValuesMapping3.put("Sec 4", 30L);
        CommandResult commandResult1 = new SecLevelTableCommandResult(titlesValuesMapping1);
        CommandResult commandResult2 = new SecLevelTableCommandResult(titlesValuesMapping2);
        CommandResult commandResult3 = new SecLevelTableCommandResult(titlesValuesMapping3);

        // same values -> returns same hashcode
        assertEquals(commandResult1.hashCode(), commandResult2.hashCode());

        // different count values -> returns different hashcode
        assertNotEquals(commandResult1.hashCode(), commandResult3.hashCode());

    }

    @Test
    public void toStringMethod() {
        Map<String, Long> titlesValuesMapping = new HashMap<>();
        titlesValuesMapping.put("Sec 1", 20L);
        titlesValuesMapping.put("Sec 2", 30L);
        titlesValuesMapping.put("Sec 3", 40L);
        titlesValuesMapping.put("Sec 4", 50L);
        CommandResult commandResult = new SecLevelTableCommandResult(titlesValuesMapping);
        String expected = SecLevelTableCommandResult.class.getCanonicalName() + "{feedbackToUser="
                + commandResult.getFeedbackToUser() + ", showHelp=" + commandResult.isShowHelp()
                + ", showTable=" + commandResult.isShowTable()
                + ", exit=" + commandResult.isExit() + "}";
        assertEquals(expected, commandResult.toString());
    }

    @Test
    public void parseMappingCorrectly() {
        Map<String, Long> titlesValuesMapping = new HashMap<>();
        titlesValuesMapping.put("Sec 1", 20L);
        titlesValuesMapping.put("Sec 2", 30L);
        titlesValuesMapping.put("Sec 3", 40L);
        titlesValuesMapping.put("Sec 4", 50L);
        SecLevelTableCommandResult commandResult = new SecLevelTableCommandResult(titlesValuesMapping);

        assertEquals(commandResult.getSec1Count(), 20L);
        assertEquals(commandResult.getSec2Count(), 30L);
        assertEquals(commandResult.getSec3Count(), 40L);
        assertEquals(commandResult.getSec4Count(), 50L);
    }
}
