package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Phone(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // EP: null phone number
        assertThrows(NullPointerException.class, () -> Phone.isValidPhone(null));

        // invalid phone numbers
        // EP: empty strings
        assertFalse(Phone.isValidPhone("")); // empty string
        assertFalse(Phone.isValidPhone(" ")); // spaces only

        // EP: non-numeric strings
        assertFalse(Phone.isValidPhone("Stephens")); // contains alphabetical characters
        assertFalse(Phone.isValidPhone("!@#$%^&*")); // contains special characters

        // EP: 7 characters
        assertFalse(Phone.isValidPhone("1234567")); // 7 characters exactly

        // EP: 9 characters
        assertFalse(Phone.isValidPhone("123456789")); // 9 characters exactly

        // EP: spaces between digits
        assertFalse(Phone.isValidPhone("9312 1534")); // spaces within digits


        // valid phone numbers
        // EP: 8 characters
        assertTrue(Phone.isValidPhone("91141523")); // exactly 8 numbers
        assertTrue(Phone.isValidPhone("12345678")); // any 8
    }

    @Test
    public void equals() {
        Phone phone = new Phone("99999999");

        // same values -> returns true
        assertTrue(phone.equals(new Phone("99999999")));

        // same object -> returns true
        assertTrue(phone.equals(phone));

        // null -> returns false
        assertFalse(phone.equals(null));

        // different types -> returns false
        assertFalse(phone.equals(5.0f));

        // different values -> returns false
        assertFalse(phone.equals(new Phone("99555599")));
    }
}
