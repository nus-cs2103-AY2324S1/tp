package seedu.address.logic.commands.tableresults;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
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

    public CommandResult getEnrolDateTableCommandResultSample1() {
        Map<String, Integer> titlesValuesMapping1 = new HashMap<>();
        titlesValuesMapping1.put("Jan", 50);
        titlesValuesMapping1.put("Feb", 10);
        titlesValuesMapping1.put("Mar", 30);
        titlesValuesMapping1.put("Apr", 20);
        titlesValuesMapping1.put("May", 40);
        titlesValuesMapping1.put("Jun", 50);
        titlesValuesMapping1.put("Jul", 25);
        titlesValuesMapping1.put("Aug", 15);
        titlesValuesMapping1.put("Sep", 20);
        titlesValuesMapping1.put("Oct", 5);
        titlesValuesMapping1.put("Nov", 3);
        titlesValuesMapping1.put("Dec", 100);
        CommandResult commandResult1 = new EnrolDateTableCommandResult(titlesValuesMapping1);
        return commandResult1;
    }

    public CommandResult getEnrolDateTableCommandResultSample2() {
        Map<String, Integer> titlesValuesMapping2 = new HashMap<>();
        titlesValuesMapping2.put("Jan", 30);
        titlesValuesMapping2.put("Feb", 15);
        titlesValuesMapping2.put("Mar", 20);
        titlesValuesMapping2.put("Apr", 10);
        titlesValuesMapping2.put("May", 100);
        titlesValuesMapping2.put("Jun", 21);
        titlesValuesMapping2.put("Jul", 32);
        titlesValuesMapping2.put("Aug", 15);
        titlesValuesMapping2.put("Sep", 20);
        titlesValuesMapping2.put("Oct", 5);
        titlesValuesMapping2.put("Nov", 5);
        titlesValuesMapping2.put("Dec", 100);
        CommandResult commandResult2 = new EnrolDateTableCommandResult(titlesValuesMapping2);
        return commandResult2;
    }
}
