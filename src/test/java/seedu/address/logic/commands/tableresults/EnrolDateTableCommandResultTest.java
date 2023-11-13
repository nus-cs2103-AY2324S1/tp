package seedu.address.logic.commands.tableresults;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.tag.EnrolDate;

public class EnrolDateTableCommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult1 = getEnrolDateTableCommandResultSample1();
        CommandResult commandResult2 = getEnrolDateTableCommandResultSample1();
        CommandResult commandResult3 = getEnrolDateTableCommandResultSample2();

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
        CommandResult commandResult1 = getEnrolDateTableCommandResultSample1();
        CommandResult commandResult2 = getEnrolDateTableCommandResultSample1();
        CommandResult commandResult3 = getEnrolDateTableCommandResultSample2();

        // same values -> return same hashcode
        assertEquals(commandResult1.hashCode(), commandResult2.hashCode());

        // different count values -> return different hashcode
        assertNotEquals(commandResult1.hashCode(), commandResult3.hashCode());

    }

    @Test
    public void toStringMethod() {
        CommandResult commandResult = getEnrolDateTableCommandResultSample1();
        String expected = EnrolDateTableCommandResult.class.getCanonicalName() + "{feedbackToUser="
                + commandResult.getFeedbackToUser() + ", showHelp=" + commandResult.isShowHelp()
                + ", showTable=" + commandResult.isShowTable()
                + ", showBarChart=" + commandResult.isShowBarChart()
                + ", exit=" + commandResult.isExit() + "}";

        assertEquals(expected, commandResult.toString());
    }

    public EnrolDateTableCommandResult getEnrolDateTableCommandResultSample1() {
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
        EnrolDateTableCommandResult commandResult1 = new EnrolDateTableCommandResult(titlesValuesMapping1);
        return commandResult1;
    }

    public EnrolDateTableCommandResult getEnrolDateTableCommandResultSample2() {
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
        EnrolDateTableCommandResult commandResult2 = new EnrolDateTableCommandResult(titlesValuesMapping2);
        return commandResult2;
    }
}
