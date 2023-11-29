package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.dentist.Yoe;

public class YoeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Yoe(null));
    }

    @Test
    public void constructor_invalidYoe_throwsIllegalArgumentException() {
        String invalidYoe = "";
        assertThrows(IllegalArgumentException.class, () -> new Yoe(invalidYoe));
    }

    @Test
    public void isValidYoe() {
        // null yoe
        assertThrows(NullPointerException.class, () -> Yoe.isValidYoe(null));

        // invalid Yoe
        assertFalse(Yoe.isValidYoe("")); // empty string
        assertFalse(Yoe.isValidYoe(" ")); // spaces only
        assertFalse(Yoe.isValidYoe("^")); // only non-alphanumeric characters
        assertFalse(Yoe.isValidYoe("peter*")); // contains non-alphanumeric characters
        assertFalse(Yoe.isValidYoe("12345")); // long numbers
        assertFalse(Yoe.isValidYoe("100")); // three digits
        assertFalse(Yoe.isValidYoe("1000")); // four digits


        // valid Yoe
        assertTrue(Yoe.isValidYoe("1")); // one digit
        assertTrue(Yoe.isValidYoe("10")); // two digits
    }

    @Test
    public void equals() {
        Yoe yoe = new Yoe("1");

        // same values -> returns true
        assertTrue(yoe.equals(new Yoe("1")));

        // same object -> returns true
        assertTrue(yoe.equals(yoe));

        // null -> returns false
        assertFalse(yoe.equals(null));

        // different types -> returns false
        assertFalse(yoe.equals(5.0f));

        // different values -> returns false
        assertFalse(yoe.equals(new Yoe("2")));
    }
}
