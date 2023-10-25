package seedu.address.model.week;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class WeekTest {

    @Test
    public void constructor_invalidWeekNumber_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Week(14));
    }

    @Test
    public void isValidWeek() {
        // invalid week number
        assertFalse(Week.isValidWeek(14));
        assertFalse(Week.isValidWeek(-1));

        // valid week number
        assertTrue(Week.isValidWeek(5));
        assertTrue(Week.isValidWeek(0));
    }

    @Test
    public void getWeekNumber() {
        int weekNumber = 5;
        Week week = new Week(weekNumber);
        assertEquals(weekNumber, week.getWeekNumber());
    }

    @Test
    public void equals() {
        Week firstWeek = new Week(1);
        Week secondWeek = new Week(2);

        // same object -> returns true
        assertEquals(firstWeek, firstWeek);

        // same values -> returns true
        Week firstWeekCopy = new Week(1);
        assertEquals(firstWeekCopy, firstWeek);

        // different types -> returns false
        assertNotEquals(1, firstWeek);

        // null -> returns false
        assertNotEquals(null, firstWeek);

        // different person -> returns false
        assertNotEquals(firstWeek, secondWeek);
    }
}
