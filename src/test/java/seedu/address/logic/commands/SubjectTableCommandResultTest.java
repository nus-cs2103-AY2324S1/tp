package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class SubjectTableCommandResultTest {
    @Test
    public void equals() {
        Map<String, Long> titlesValuesMapping1 = new HashMap<>();
        titlesValuesMapping1.put("Computer Science", 20L);
        titlesValuesMapping1.put("Mathematics", 30L);
        titlesValuesMapping1.put("Chemistry", 40L);
        titlesValuesMapping1.put("Physics", 50L);
        titlesValuesMapping1.put("Biology", 50L);
        titlesValuesMapping1.put("English", 50L);
        Map<String, Long> titlesValuesMapping2 = new HashMap<>();
        titlesValuesMapping2.put("Computer Science", 20L);
        titlesValuesMapping2.put("Mathematics", 30L);
        titlesValuesMapping2.put("Chemistry", 40L);
        titlesValuesMapping2.put("Physics", 50L);
        titlesValuesMapping2.put("Biology", 50L);
        titlesValuesMapping2.put("English", 50L);
        Map<String, Long> titlesValuesMapping3 = new HashMap<>();
        titlesValuesMapping3.put("Computer Science", 10L);
        titlesValuesMapping3.put("Mathematics", 10L);
        titlesValuesMapping3.put("Chemistry", 20L);
        titlesValuesMapping3.put("Physics", 20L);
        titlesValuesMapping3.put("Biology", 30L);
        titlesValuesMapping3.put("English", 30L);
        CommandResult commandResult1 = new SubjectTableCommandResult(titlesValuesMapping1);
        CommandResult commandResult2 = new SubjectTableCommandResult(titlesValuesMapping2);
        CommandResult commandResult3 = new SubjectTableCommandResult(titlesValuesMapping3);

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
        titlesValuesMapping1.put("Computer Science", 20L);
        titlesValuesMapping1.put("Mathematics", 30L);
        titlesValuesMapping1.put("Chemistry", 40L);
        titlesValuesMapping1.put("Physics", 50L);
        titlesValuesMapping1.put("Biology", 50L);
        titlesValuesMapping1.put("English", 50L);
        Map<String, Long> titlesValuesMapping2 = new HashMap<>();
        titlesValuesMapping2.put("Computer Science", 20L);
        titlesValuesMapping2.put("Mathematics", 30L);
        titlesValuesMapping2.put("Chemistry", 40L);
        titlesValuesMapping2.put("Physics", 50L);
        titlesValuesMapping2.put("Biology", 50L);
        titlesValuesMapping2.put("English", 50L);
        Map<String, Long> titlesValuesMapping3 = new HashMap<>();
        titlesValuesMapping3.put("Computer Science", 10L);
        titlesValuesMapping3.put("Mathematics", 10L);
        titlesValuesMapping3.put("Chemistry", 20L);
        titlesValuesMapping3.put("Physics", 20L);
        titlesValuesMapping3.put("Biology", 30L);
        titlesValuesMapping3.put("English", 30L);
        CommandResult commandResult1 = new SubjectTableCommandResult(titlesValuesMapping1);
        CommandResult commandResult2 = new SubjectTableCommandResult(titlesValuesMapping2);
        CommandResult commandResult3 = new SubjectTableCommandResult(titlesValuesMapping3);

        // same values -> returns same hashcode
        assertEquals(commandResult1.hashCode(), commandResult2.hashCode());

        // different count values -> returns different hashcode
        assertNotEquals(commandResult1.hashCode(), commandResult3.hashCode());

    }

    @Test
    public void toStringMethod() {
        Map<String, Long> titlesValuesMapping = new HashMap<>();
        titlesValuesMapping.put("Computer Science", 20L);
        titlesValuesMapping.put("Mathematics", 30L);
        titlesValuesMapping.put("Chemistry", 40L);
        titlesValuesMapping.put("Physics", 50L);
        titlesValuesMapping.put("Biology", 50L);
        titlesValuesMapping.put("English", 50L);
        CommandResult commandResult = new SubjectTableCommandResult(titlesValuesMapping);
        String expected = SubjectTableCommandResult.class.getCanonicalName() + "{feedbackToUser="
                + commandResult.getFeedbackToUser() + ", showHelp=" + commandResult.isShowHelp()
                + ", showTable=" + commandResult.isShowTable()
                + ", exit=" + commandResult.isExit() + "}";
        assertEquals(expected, commandResult.toString());
    }

    @Test
    public void parseMappingCorrectly() {
        Map<String, Long> titlesValuesMapping = new HashMap<>();
        titlesValuesMapping.put("Computer Science", 20L);
        titlesValuesMapping.put("Mathematics", 30L);
        titlesValuesMapping.put("Chemistry", 40L);
        titlesValuesMapping.put("Physics", 50L);
        titlesValuesMapping.put("Biology", 60L);
        titlesValuesMapping.put("English", 70L);
        SubjectTableCommandResult commandResult = new SubjectTableCommandResult(titlesValuesMapping);

        assertEquals(commandResult.getCsCount(), 20L);
        assertEquals(commandResult.getMathsCount(), 30L);
        assertEquals(commandResult.getChemiCount(), 40L);
        assertEquals(commandResult.getPhyCount(), 50L);
        assertEquals(commandResult.getBioCount(), 60L);
        assertEquals(commandResult.getEngCount(), 70L);
    }
}
