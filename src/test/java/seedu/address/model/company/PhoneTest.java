package seedu.address.model.company;

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
        // null phone number
        assertThrows(NullPointerException.class, () -> Phone.isValidPhone(null));

        // invalid phone numbers without spaces
        assertFalse(Phone.isValidPhone("")); // empty string
        assertFalse(Phone.isValidPhone("91")); // less than 3 digits
        assertFalse(Phone.isValidPhone("-91")); // contains non-numeric characters
        assertFalse(Phone.isValidPhone("phone")); // non-numeric
        assertFalse(Phone.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(Phone.isValidPhone("123456789012345678901")); // more than 20 characters (21 characters)

        // invalid phone numbers with spaces
        assertFalse(Phone.isValidPhone("    ")); // spaces only
        assertFalse(Phone.isValidPhone("1     1")); // less than 3 digits
        assertFalse(Phone.isValidPhone("         1     1          ")); // less than 3 digits
        assertFalse(Phone.isValidPhone("1234 567890 1234567 8901")); // 21 characters excluding spaces

        // valid phone numbers without spaces
        assertTrue(Phone.isValidPhone("911")); // exactly 3 numbers
        assertTrue(Phone.isValidPhone("93121534"));
        assertTrue(Phone.isValidPhone("12345678901234567890")); // exactly 20 characters

        // valid phone numbers with spaces
        assertTrue(Phone.isValidPhone("1    1         1       ")); // exactly 3 digits
        assertTrue(Phone.isValidPhone("1 1 1 1 1 1 1 1 1 1 1 1 1 1 1")); // 15 characters excluding spaces
        assertTrue(Phone.isValidPhone("1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0")); // 20 characters excluding spaces
    }

    @Test
    public void equals() {
        Phone phone = new Phone("999");

        // same values -> returns true
        assertTrue(phone.equals(new Phone("999")));

        // same object -> returns true
        assertTrue(phone.equals(phone));

        // null -> returns false
        assertFalse(phone.equals(null));

        // different types -> returns false
        assertFalse(phone.equals(5.0f));

        // different values -> returns false
        assertFalse(phone.equals(new Phone("995")));
    }
}
