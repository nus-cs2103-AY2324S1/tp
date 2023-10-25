package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class BirthdayTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Birthday(null));
    }

    @Test
    public void isValidBirthday() {
        // null birthday
        assertThrows(NullPointerException.class, () -> Birthday.isValidBirthday(null));

        // invalid birthday
        assertFalse(Birthday.isValidBirthday("^")); // only non-alphanumeric characters
        assertFalse(Birthday.isValidBirthday("peter*")); // contains non-alphanumeric characters
        assertFalse(Birthday.isValidBirthday("12-12-2001")); // invalid format
        assertFalse(Birthday.isValidBirthday("12-12")); // missing year
        assertFalse(Birthday.isValidBirthday("12-2001")); // missing day or month
        assertFalse(Birthday.isValidBirthday("2001-13-12")); // invalid month
        assertFalse(Birthday.isValidBirthday("2001-12-32")); // invalid day

        // valid birthday
        assertTrue(Birthday.isValidBirthday("")); // empty string
        assertTrue(Birthday.isValidBirthday(" ")); // spaces only
        assertTrue(Birthday.isValidBirthday("2001-12-14")); // valid format
    }

    @Test
    public void equals() {
        String validBirthday = "2001-12-14";
        Birthday birthday = new Birthday(validBirthday);

        // same values -> returns true
        assertTrue(birthday.equals(new Birthday(validBirthday)));

        // same object -> returns true
        assertTrue(birthday.equals(birthday));

        // null -> returns false
        assertFalse(birthday.equals(null));

        // different types -> returns false
        assertFalse(birthday.equals(5.0f));

        // different values -> returns false
        assertFalse(birthday.equals(new Birthday("2001-12-15")));
    }

}
