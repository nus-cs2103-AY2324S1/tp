package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
public class SecLevelTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SecLevel(null));
    }

    @Test
    public void constructor_invalidSecLevel_throwsIllegalArgumentException() {
        String invalidSecLevel = "";
        assertThrows(IllegalArgumentException.class, () -> new SecLevel(invalidSecLevel));
    }

    @Test
    public void isValidSecLevel() {
        // null sec level
        assertThrows(NullPointerException.class, () -> SecLevel.isValidSecLevel(null));

        // invalid sec level
        assertFalse(SecLevel.isValidSecLevel("")); // empty string
        assertFalse(SecLevel.isValidSecLevel(" ")); // spaces only
        assertFalse(SecLevel.isValidSecLevel("^")); // onlly non-alphanumeric characters
        assertFalse(SecLevel.isValidSecLevel("5")); // sec level that is not from 1 to 4

        // valid sec level
        assertTrue(SecLevel.isValidSecLevel("1")); // sec level 1
        assertTrue(SecLevel.isValidSecLevel("2")); // sec level 2
        assertTrue(SecLevel.isValidSecLevel("3")); // sec level 3
        assertTrue(SecLevel.isValidSecLevel("4")); // sec level 4
    }

    @Test
    public void equalsMethod() {
        SecLevel sec1 = new SecLevel("1");
        SecLevel anotherSec1 = new SecLevel("1");
        SecLevel sec4 = new SecLevel("4");

        // same values -> return true
        assertTrue(sec1.equals(anotherSec1));

        // same object -> return true
        assertTrue(sec1.equals(sec1));
        assertTrue(sec4.equals(sec4));

        // null -> return false
        assertFalse(sec1.equals(null));

        // different types -> return false
        assertFalse(sec1.equals(5.0f));

        //different values -> return false
        assertFalse(sec1.equals(sec4));

    }

    @Test
    public void getUpLevel() {
        SecLevel sec1 = new SecLevel("1");
        SecLevel sec4 = new SecLevel("4");
        SecLevel sec2 = new SecLevel("2");
        SecLevel anotherSec4 = new SecLevel("4");

        assertTrue(sec2.equals(sec1.getUpSecLevel()));
        assertTrue(anotherSec4.equals(sec4.getUpSecLevel()));
    }
}
