package seedu.address.logic.commands.trendresults;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;

public class TrendCommandResultTest {
    @Test
    public void equals() {
        Map<String, Integer> titlesValuesMapping1 = new HashMap<>();
        titlesValuesMapping1.put("Jan", 50);
        titlesValuesMapping1.put("Feb", 10);
        titlesValuesMapping1.put("Mar", 30);
        titlesValuesMapping1.put("Apr", 20);
        titlesValuesMapping1.put("May", 40);
        titlesValuesMapping1.put("Jun", 50);
        titlesValuesMapping1.put("Jul", 25);
        titlesValuesMapping1.put("Aug", 15);
        titlesValuesMapping1.put("Sep", 25);
        titlesValuesMapping1.put("Oct", 20);
        titlesValuesMapping1.put("Nov", 5);
        titlesValuesMapping1.put("Dec", 25);

        Map<String, Integer> titlesValuesMapping2 = new HashMap<>();
        titlesValuesMapping2.put("Jan", 50);
        titlesValuesMapping2.put("Feb", 10);
        titlesValuesMapping2.put("Mar", 30);
        titlesValuesMapping2.put("Apr", 20);
        titlesValuesMapping2.put("May", 40);
        titlesValuesMapping2.put("Jun", 50);
        titlesValuesMapping2.put("Jul", 25);
        titlesValuesMapping2.put("Aug", 15);
        titlesValuesMapping2.put("Sep", 25);
        titlesValuesMapping2.put("Oct", 20);
        titlesValuesMapping2.put("Nov", 5);
        titlesValuesMapping2.put("Dec", 25);

        Map<String, Integer> titlesValuesMapping3 = new HashMap<>();
        titlesValuesMapping3.put("Jan", 50);
        titlesValuesMapping3.put("Feb", 15);
        titlesValuesMapping3.put("Mar", 25);
        titlesValuesMapping3.put("Apr", 20);
        titlesValuesMapping3.put("May", 40);
        titlesValuesMapping3.put("Jun", 50);
        titlesValuesMapping3.put("Jul", 25);
        titlesValuesMapping3.put("Aug", 15);
        titlesValuesMapping3.put("Sep", 20);
        titlesValuesMapping3.put("Oct", 20);
        titlesValuesMapping3.put("Nov", 5);
        titlesValuesMapping3.put("Dec", 25);

        CommandResult commandResult1 = new TrendCommandResult(titlesValuesMapping1);
        CommandResult commandResult2 = new TrendCommandResult(titlesValuesMapping2);
        CommandResult commandResult3 = new TrendCommandResult(titlesValuesMapping3);

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
        titlesValuesMapping1.put("Jan", 50);
        titlesValuesMapping1.put("Feb", 10);
        titlesValuesMapping1.put("Mar", 30);
        titlesValuesMapping1.put("Apr", 20);
        titlesValuesMapping1.put("May", 40);
        titlesValuesMapping1.put("Jun", 50);
        titlesValuesMapping1.put("Jul", 25);
        titlesValuesMapping1.put("Aug", 15);
        titlesValuesMapping1.put("Sep", 25);
        titlesValuesMapping1.put("Oct", 20);
        titlesValuesMapping1.put("Nov", 5);
        titlesValuesMapping1.put("Dec", 25);

        Map<String, Integer> titlesValuesMapping2 = new HashMap<>();
        titlesValuesMapping2.put("Jan", 50);
        titlesValuesMapping2.put("Feb", 10);
        titlesValuesMapping2.put("Mar", 30);
        titlesValuesMapping2.put("Apr", 20);
        titlesValuesMapping2.put("May", 40);
        titlesValuesMapping2.put("Jun", 50);
        titlesValuesMapping2.put("Jul", 25);
        titlesValuesMapping2.put("Aug", 15);
        titlesValuesMapping2.put("Sep", 25);
        titlesValuesMapping2.put("Oct", 20);
        titlesValuesMapping2.put("Nov", 5);
        titlesValuesMapping2.put("Dec", 25);

        Map<String, Integer> titlesValuesMapping3 = new HashMap<>();
        titlesValuesMapping3.put("Jan", 50);
        titlesValuesMapping3.put("Feb", 15);
        titlesValuesMapping3.put("Mar", 25);
        titlesValuesMapping3.put("Apr", 20);
        titlesValuesMapping3.put("May", 40);
        titlesValuesMapping3.put("Jun", 50);
        titlesValuesMapping3.put("Jul", 25);
        titlesValuesMapping3.put("Aug", 15);
        titlesValuesMapping3.put("Sep", 20);
        titlesValuesMapping3.put("Oct", 20);
        titlesValuesMapping3.put("Nov", 5);
        titlesValuesMapping3.put("Dec", 25);

        CommandResult commandResult1 = new TrendCommandResult(titlesValuesMapping1);
        CommandResult commandResult2 = new TrendCommandResult(titlesValuesMapping2);
        CommandResult commandResult3 = new TrendCommandResult(titlesValuesMapping3);

        // same values -> returns same hashcode
        assertEquals(commandResult1.hashCode(), commandResult2.hashCode());

        // different count values -> returns different hashcode
        assertNotEquals(commandResult1.hashCode(), commandResult3.hashCode());

    }

    @Test
    public void toStringMethod() {
        Map<String, Integer> titlesValuesMapping1 = new HashMap<>();
        titlesValuesMapping1.put("Jan", 50);
        titlesValuesMapping1.put("Feb", 10);
        titlesValuesMapping1.put("Mar", 30);
        titlesValuesMapping1.put("Apr", 20);
        titlesValuesMapping1.put("May", 40);
        titlesValuesMapping1.put("Jun", 50);
        titlesValuesMapping1.put("Jul", 25);
        titlesValuesMapping1.put("Aug", 15);
        titlesValuesMapping1.put("Sep", 25);
        titlesValuesMapping1.put("Oct", 20);
        titlesValuesMapping1.put("Nov", 5);
        titlesValuesMapping1.put("Dec", 25);

        CommandResult commandResult1 = new TrendCommandResult(titlesValuesMapping1);

        String expected = TrendCommandResult.class.getCanonicalName()
                + "{feedbackToUser=" + commandResult1.getFeedbackToUser()
                + ", showHelp=" + commandResult1.isShowHelp()
                + ", showTable=" + commandResult1.isShowTable()
                + ", showBarChart=" + commandResult1.isShowBarChart()
                + ", showTrend=" + commandResult1.isShowTrend()
                + ", exit=" + commandResult1.isExit() + "}";

        assertEquals(expected, commandResult1.toString());

    }

    @Test
    public void parseMappingCorrectly() {
        Map<String, Integer> titlesValuesMapping1 = new HashMap<>();
        titlesValuesMapping1.put("Jan", 50);
        titlesValuesMapping1.put("Feb", 10);
        titlesValuesMapping1.put("Mar", 30);
        titlesValuesMapping1.put("Apr", 20);
        titlesValuesMapping1.put("May", 40);
        titlesValuesMapping1.put("Jun", 50);
        titlesValuesMapping1.put("Jul", 25);
        titlesValuesMapping1.put("Aug", 15);
        titlesValuesMapping1.put("Sep", 25);
        titlesValuesMapping1.put("Oct", 20);
        titlesValuesMapping1.put("Nov", 5);
        titlesValuesMapping1.put("Dec", 25);

        TrendCommandResult commandResult1 = new TrendCommandResult(titlesValuesMapping1);

        assertEquals(commandResult1.getJanCount(), 50);
        assertEquals(commandResult1.getFebCount(), 10);
        assertEquals(commandResult1.getMarCount(), 30);
        assertEquals(commandResult1.getAprCount(), 20);
        assertEquals(commandResult1.getMayCount(), 40);
        assertEquals(commandResult1.getJunCount(), 50);
        assertEquals(commandResult1.getJulCount(), 25);
        assertEquals(commandResult1.getAugCount(), 15);
        assertEquals(commandResult1.getSepCount(), 25);
        assertEquals(commandResult1.getOctCount(), 20);
        assertEquals(commandResult1.getNovCount(), 5);
        assertEquals(commandResult1.getDecCount(), 25);
    }

}
