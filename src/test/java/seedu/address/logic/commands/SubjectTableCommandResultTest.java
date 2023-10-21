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
        Map<String, Integer> titlesValuesMapping1 = new HashMap<>();
        titlesValuesMapping1.put("Computer Science", 20);
        titlesValuesMapping1.put("Mathematics", 30);
        titlesValuesMapping1.put("Chemistry", 40);
        titlesValuesMapping1.put("Physics", 50);
        titlesValuesMapping1.put("Biology", 50);
        titlesValuesMapping1.put("English", 50);
        Map<String, Integer> titlesValuesMapping2 = new HashMap<>();
        titlesValuesMapping2.put("Computer Science", 20);
        titlesValuesMapping2.put("Mathematics", 30);
        titlesValuesMapping2.put("Chemistry", 40);
        titlesValuesMapping2.put("Physics", 50);
        titlesValuesMapping2.put("Biology", 50);
        titlesValuesMapping2.put("English", 50);
        Map<String, Integer> titlesValuesMapping3 = new HashMap<>();
        titlesValuesMapping3.put("Computer Science", 10);
        titlesValuesMapping3.put("Mathematics", 10);
        titlesValuesMapping3.put("Chemistry", 20);
        titlesValuesMapping3.put("Physics", 20);
        titlesValuesMapping3.put("Biology", 30);
        titlesValuesMapping3.put("English", 30);
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
        Map<String, Integer> titlesValuesMapping1 = new HashMap<>();
        titlesValuesMapping1.put("Computer Science", 20);
        titlesValuesMapping1.put("Mathematics", 30);
        titlesValuesMapping1.put("Chemistry", 40);
        titlesValuesMapping1.put("Physics", 50);
        titlesValuesMapping1.put("Biology", 50);
        titlesValuesMapping1.put("English", 50);
        Map<String, Integer> titlesValuesMapping2 = new HashMap<>();
        titlesValuesMapping2.put("Computer Science", 20);
        titlesValuesMapping2.put("Mathematics", 30);
        titlesValuesMapping2.put("Chemistry", 40);
        titlesValuesMapping2.put("Physics", 50);
        titlesValuesMapping2.put("Biology", 50);
        titlesValuesMapping2.put("English", 50);
        Map<String, Integer> titlesValuesMapping3 = new HashMap<>();
        titlesValuesMapping3.put("Computer Science", 10);
        titlesValuesMapping3.put("Mathematics", 10);
        titlesValuesMapping3.put("Chemistry", 20);
        titlesValuesMapping3.put("Physics", 20);
        titlesValuesMapping3.put("Biology", 30);
        titlesValuesMapping3.put("English", 30);
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
        Map<String, Integer> titlesValuesMapping = new HashMap<>();
        titlesValuesMapping.put("Computer Science", 20);
        titlesValuesMapping.put("Mathematics", 30);
        titlesValuesMapping.put("Chemistry", 40);
        titlesValuesMapping.put("Physics", 50);
        titlesValuesMapping.put("Biology", 50);
        titlesValuesMapping.put("English", 50);
        CommandResult commandResult = new SubjectTableCommandResult(titlesValuesMapping);
        String expected = SubjectTableCommandResult.class.getCanonicalName() + "{feedbackToUser="
                + commandResult.getFeedbackToUser() + ", showHelp=" + commandResult.isShowHelp()
                + ", showTable=" + commandResult.isShowTable()
                + ", exit=" + commandResult.isExit() + "}";
        assertEquals(expected, commandResult.toString());
    }

    @Test
    public void parseMappingCorrectly() {
        Map<String, Integer> titlesValuesMapping = new HashMap<>();
        titlesValuesMapping.put("Computer Science", 20);
        titlesValuesMapping.put("Mathematics", 30);
        titlesValuesMapping.put("Chemistry", 40);
        titlesValuesMapping.put("Physics", 50);
        titlesValuesMapping.put("Biology", 60);
        titlesValuesMapping.put("English", 70);
        SubjectTableCommandResult commandResult = new SubjectTableCommandResult(titlesValuesMapping);

        assertEquals(commandResult.getCsCount(), 20);
        assertEquals(commandResult.getMathsCount(), 30);
        assertEquals(commandResult.getChemiCount(), 40);
        assertEquals(commandResult.getPhyCount(), 50);
        assertEquals(commandResult.getBioCount(), 60);
        assertEquals(commandResult.getEngCount(), 70);
    }
}
