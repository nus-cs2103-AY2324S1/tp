package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;


public class FreeTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FreeTime(null, null));
    }

    @Test
    public void constructor_invalidFreeTime_throwsIllegalArgumentException() {
        LocalTime curr = LocalTime.now();
        assertThrows(IllegalArgumentException.class, () -> new FreeTime(curr, curr));
    }

    @Test
    public void isValidFreeTime() {
        // null from and to
        assertThrows(NullPointerException.class, () -> FreeTime.isValidFreeTime(null, null));
        LocalTime from = LocalTime.parse("12:20");
        LocalTime to = LocalTime.parse("23:44");
        LocalTime closeFrom = LocalTime.parse("12:21");

        // invalid free time
        assertFalse(FreeTime.isValidFreeTime(from, from)); // same from and to
        assertFalse(FreeTime.isValidFreeTime(to, from)); // to before from

        // valid free time
        assertTrue(FreeTime.isValidFreeTime(from, to)); // to after from
        assertTrue(FreeTime.isValidFreeTime(from, closeFrom)); // very short
    }

    @Test
    public void equals() {
        LocalTime from = LocalTime.parse("12:20");
        LocalTime to = LocalTime.parse("23:44");
        LocalTime closeFrom = LocalTime.parse("12:21");
        FreeTime freeTime = new FreeTime(from, to);

        // same values -> returns true
        assertTrue(freeTime.equals(new FreeTime(LocalTime.parse("12:20"), LocalTime.parse("23:44"))));

        // same object -> returns true
        assertTrue(freeTime.equals(freeTime));

        // null -> returns false
        assertFalse(freeTime.equals(null));

        // different types -> returns false
        assertFalse(freeTime.equals(5.0f));

        // different values -> returns false
        assertFalse(freeTime.equals(new FreeTime(from, closeFrom)));
    }
}
