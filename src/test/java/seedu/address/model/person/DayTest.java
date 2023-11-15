package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DayTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Day(null));
    }

    @Test
    public void constructor_invalidDay_throwsIllegalArgumentException() {
        String emptyDay = "";
        assertThrows(IllegalArgumentException.class, () -> new Day(emptyDay));

        String invalidDay = "Tues";
        assertThrows(IllegalArgumentException.class, () -> new PayRate(invalidDay));
    }

    @Test
    public void equals() {
        Day day = new Day("MON");

        // same values -> returns true
        assertTrue(day.equals(new Day("MON")));

        // same values -> returns true
        assertTrue(day.equals(new Day("Monday")));

        // same object -> returns true
        assertTrue(day.equals(day));

        // null -> returns false
        assertFalse(day.equals(null));

        // different types -> returns false
        assertFalse(day.equals(5.0f));

        // different values -> returns false
        assertFalse(day.equals(new Day("Tue")));
    }
}
