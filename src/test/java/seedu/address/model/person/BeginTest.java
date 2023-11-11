package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.address.model.interval.IntervalBegin;

public class BeginTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Begin(null));
    }

    @Test
    public void equals() {
        Begin begin = new Begin("1800");

        // same values -> returns true
        assertTrue(begin.equals(new Begin("1800")));

        // same object -> returns true
        assertTrue(begin.equals(begin));

        // null -> returns false
        assertFalse(begin.equals(null));

        // different types -> returns false
        assertFalse(begin.equals(1800));

        // different values -> returns false
        assertFalse(begin.equals(new IntervalBegin("1900")));
    }
}
