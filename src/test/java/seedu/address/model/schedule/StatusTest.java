package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.model.schedule.Status.parseStatusToString;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;



public class StatusTest {
    private static final Status statusMissed = Status.MISSED;
    private static final Status statusCompleted = Status.COMPLETED;
    private static final Status invalidStatus = Status.PENDING;

    @Test
    public void parseStatusToString_returnsStatusString() throws Exception {
        String expectedMissedString = "0";
        String expectedCompletedString = "1";
        assertEquals(parseStatusToString(statusMissed), expectedMissedString);
        assertEquals(parseStatusToString(statusCompleted), expectedCompletedString);
        assertThrows(ParseException.class, () -> parseStatusToString(invalidStatus));
    }
}
