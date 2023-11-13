package seedu.address.logic.commands.barchartresults;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;

class SecLevelBarChartCommandResultTest {

    @Test
    public void constructor_nullColumnValue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SecLevelBarChartCommandResult(null));
    }

    @Test
    public void equals() {
        Map<String, Integer> titlesValuesMapping1 = new HashMap<>();
        titlesValuesMapping1.put("Sec 1", 20);
        titlesValuesMapping1.put("Sec 2", 30);
        titlesValuesMapping1.put("Sec 3", 40);
        titlesValuesMapping1.put("Sec 4", 50);
        Map<String, Integer> titlesValuesMapping2 = new HashMap<>();
        titlesValuesMapping2.put("Sec 1", 20);
        titlesValuesMapping2.put("Sec 2", 30);
        titlesValuesMapping2.put("Sec 3", 40);
        titlesValuesMapping2.put("Sec 4", 50);
        Map<String, Integer> titlesValuesMapping3 = new HashMap<>();
        titlesValuesMapping3.put("Sec 1", 10);
        titlesValuesMapping3.put("Sec 2", 10);
        titlesValuesMapping3.put("Sec 3", 20);
        titlesValuesMapping3.put("Sec 4", 30);
        CommandResult commandResult1 = new SecLevelBarChartCommandResult(titlesValuesMapping1);
        CommandResult commandResult2 = new SecLevelBarChartCommandResult(titlesValuesMapping2);
        CommandResult commandResult3 = new SecLevelBarChartCommandResult(titlesValuesMapping3);

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
        Map<String, Integer> titlesValuesMapping1 = new HashMap<>();
        titlesValuesMapping1.put("Sec 1", 20);
        titlesValuesMapping1.put("Sec 2", 30);
        titlesValuesMapping1.put("Sec 3", 40);
        titlesValuesMapping1.put("Sec 4", 50);
        Map<String, Integer> titlesValuesMapping2 = new HashMap<>();
        titlesValuesMapping2.put("Sec 1", 20);
        titlesValuesMapping2.put("Sec 2", 30);
        titlesValuesMapping2.put("Sec 3", 40);
        titlesValuesMapping2.put("Sec 4", 50);
        Map<String, Integer> titlesValuesMapping3 = new HashMap<>();
        titlesValuesMapping3.put("Sec 1", 10);
        titlesValuesMapping3.put("Sec 2", 10);
        titlesValuesMapping3.put("Sec 3", 20);
        titlesValuesMapping3.put("Sec 4", 30);
        CommandResult commandResult1 = new SecLevelBarChartCommandResult(titlesValuesMapping1);
        CommandResult commandResult2 = new SecLevelBarChartCommandResult(titlesValuesMapping2);
        CommandResult commandResult3 = new SecLevelBarChartCommandResult(titlesValuesMapping3);

        // same values -> returns same hashcode
        assertEquals(commandResult1.hashCode(), commandResult2.hashCode());

        // different count values -> returns different hashcode
        assertNotEquals(commandResult1.hashCode(), commandResult3.hashCode());

    }

    @Test
    public void toStringMethod() {
        Map<String, Integer> titlesValuesMapping = new HashMap<>();
        titlesValuesMapping.put("Sec 1", 20);
        titlesValuesMapping.put("Sec 2", 30);
        titlesValuesMapping.put("Sec 3", 40);
        titlesValuesMapping.put("Sec 4", 50);
        CommandResult commandResult = new SecLevelBarChartCommandResult(titlesValuesMapping);
        String expected = SecLevelBarChartCommandResult.class.getCanonicalName() + "{feedbackToUser="
                + commandResult.getFeedbackToUser() + ", showHelp=" + commandResult.isShowHelp()
                + ", showTable=" + commandResult.isShowTable()
                + ", showBarChart=" + commandResult.isShowBarChart()
                + ", showTrend=" + commandResult.isShowTrend()
                + ", exit=" + commandResult.isExit() + "}";
        assertEquals(expected, commandResult.toString());
    }

    @Test
    public void parseMappingCorrectly() {
        Map<String, Integer> titlesValuesMapping = new HashMap<>();
        titlesValuesMapping.put("Sec 1", 20);
        titlesValuesMapping.put("Sec 2", 30);
        titlesValuesMapping.put("Sec 3", 40);
        titlesValuesMapping.put("Sec 4", 50);
        SecLevelBarChartCommandResult commandResult = new SecLevelBarChartCommandResult(titlesValuesMapping);

        assertEquals(commandResult.getSec1Count(), 20);
        assertEquals(commandResult.getSec2Count(), 30);
        assertEquals(commandResult.getSec3Count(), 40);
        assertEquals(commandResult.getSec4Count(), 50);
    }

}
