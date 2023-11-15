package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.MonthDay;

import org.junit.jupiter.api.Test;

public class BirthdayTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Birthday(null));
    }

    @Test
    public void equals() {
        Birthday birthday = new Birthday(MonthDay.of(1, 23));

        // same values -> returns true
        assertTrue(birthday.equals(new Birthday(MonthDay.of(1, 23))));

        // same object -> returns true
        assertTrue(birthday.equals(birthday));

        // null -> returns false
        assertFalse(birthday.equals(null));

        // different types -> returns false
        assertFalse(birthday.equals(5.0f));

        // different values -> returns false
        assertFalse(birthday.equals(new Birthday(MonthDay.of(11, 11))));
    }

    @Test
    public void toString_validInput_success() {
        assertEquals(new Birthday(MonthDay.of(1, 23)).toString(), "23 January");
        assertEquals(new Birthday(MonthDay.of(2, 29)).toString(), "29 February");
        assertEquals(new Birthday(MonthDay.of(3, 31)).toString(), "31 March");
        assertEquals(new Birthday(MonthDay.of(12, 1)).toString(), "1 December");
    }
}
