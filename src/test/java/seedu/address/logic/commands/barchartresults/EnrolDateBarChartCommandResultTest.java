package seedu.address.logic.commands.barchartresults;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.tag.EnrolDate;

public class EnrolDateBarChartCommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult1 = getEnrolDateBarChartCommandResultSample1();
        CommandResult commandResult2 = getEnrolDateBarChartCommandResultSample1();
        CommandResult commandResult3 = getEnrolDateBarChartCommandResultSample2();

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
        EnrolDateBarChartCommandResult commandResult1 = getEnrolDateBarChartCommandResultSample1();
        EnrolDateBarChartCommandResult commandResult2 = getEnrolDateBarChartCommandResultSample1();
        EnrolDateBarChartCommandResult commandResult3 = getEnrolDateBarChartCommandResultSample2();

        // same values -> return same hashcode
        assertEquals(commandResult1.hashCode(), commandResult2.hashCode());

        // different count values -> return different hashcode
        assertNotEquals(commandResult1.hashCode(), commandResult3.hashCode());

    }

    @Test
    public void toStringMethod() {
        CommandResult commandResult = getEnrolDateBarChartCommandResultSample1();
        String expected = EnrolDateBarChartCommandResult.class.getCanonicalName() + "{feedbackToUser="
                + commandResult.getFeedbackToUser() + ", showHelp=" + commandResult.isShowHelp()
                + ", showTable=" + commandResult.isShowTable()
                + ", showBarChart=" + commandResult.isShowBarChart()
                + ", exit=" + commandResult.isExit() + "}";

        assertEquals(expected, commandResult.toString());
    }

    @Test
    public void getMonth() {
        EnrolDateBarChartCommandResult commandResult = getEnrolDateBarChartCommandResultSample1();
        assertEquals(50, commandResult.getJanCount());
        assertEquals(10, commandResult.getFebCount());
        assertEquals(30, commandResult.getMarCount());
        assertEquals(20, commandResult.getAprCount());
        assertEquals(40, commandResult.getMayCount());
        assertEquals(50, commandResult.getJunCount());
        assertEquals(25, commandResult.getJulCount());
        assertEquals(15, commandResult.getAugCount());
        assertEquals(20, commandResult.getSepCount());
        assertEquals(5, commandResult.getOctCount());
        assertEquals(3, commandResult.getNovCount());
        assertEquals(100, commandResult.getDecCount());

    }

    public EnrolDateBarChartCommandResult getEnrolDateBarChartCommandResultSample1() {
        Map<String, Integer> titlesValuesMapping1 = new HashMap<>();
        titlesValuesMapping1.put(EnrolDate.JAN, 50);
        titlesValuesMapping1.put(EnrolDate.FEB, 10);
        titlesValuesMapping1.put(EnrolDate.MAR, 30);
        titlesValuesMapping1.put(EnrolDate.APR, 20);
        titlesValuesMapping1.put(EnrolDate.MAY, 40);
        titlesValuesMapping1.put(EnrolDate.JUN, 50);
        titlesValuesMapping1.put(EnrolDate.JUL, 25);
        titlesValuesMapping1.put(EnrolDate.AUG, 15);
        titlesValuesMapping1.put(EnrolDate.SEP, 20);
        titlesValuesMapping1.put(EnrolDate.OCT, 5);
        titlesValuesMapping1.put(EnrolDate.NOV, 3);
        titlesValuesMapping1.put(EnrolDate.DEC, 100);
        EnrolDateBarChartCommandResult commandResult1 = new EnrolDateBarChartCommandResult(titlesValuesMapping1);
        return commandResult1;
    }

    public EnrolDateBarChartCommandResult getEnrolDateBarChartCommandResultSample2() {
        Map<String, Integer> titlesValuesMapping2 = new HashMap<>();
        titlesValuesMapping2.put(EnrolDate.JAN, 30);
        titlesValuesMapping2.put(EnrolDate.FEB, 15);
        titlesValuesMapping2.put(EnrolDate.MAR, 20);
        titlesValuesMapping2.put(EnrolDate.APR, 10);
        titlesValuesMapping2.put(EnrolDate.MAY, 100);
        titlesValuesMapping2.put(EnrolDate.JUN, 21);
        titlesValuesMapping2.put(EnrolDate.JUL, 32);
        titlesValuesMapping2.put(EnrolDate.AUG, 15);
        titlesValuesMapping2.put(EnrolDate.SEP, 20);
        titlesValuesMapping2.put(EnrolDate.OCT, 5);
        titlesValuesMapping2.put(EnrolDate.NOV, 5);
        titlesValuesMapping2.put(EnrolDate.DEC, 100);
        EnrolDateBarChartCommandResult commandResult2 = new EnrolDateBarChartCommandResult(titlesValuesMapping2);
        return commandResult2;
    }
}
