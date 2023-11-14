package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PropPhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PropPhone(null));
    }

    @Test
    public void constructor_invalidPropPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new PropPhone(invalidPhone));
    }

    @Test
    public void isValidPropPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> PropPhone.isValidPhone(null));

        // invalid phone numbers
        assertFalse(PropPhone.isValidPhone("")); // empty string
        assertFalse(PropPhone.isValidPhone(" ")); // spaces only
        assertFalse(PropPhone.isValidPhone("91")); // less than 3 numbers
        assertFalse(PropPhone.isValidPhone("phone")); // non-numeric
        assertFalse(PropPhone.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(PropPhone.isValidPhone("9312 1534")); // spaces within digits
        assertFalse(PropPhone.isValidPhone("911")); // exactly 3 numbers
        assertFalse(PropPhone.isValidPhone("124293842033123")); // long phone numbers
        assertFalse(PropPhone.isValidPhone("11234578"));

        // valid phone numbers
        assertTrue(PropPhone.isValidPhone("93121534"));
        assertTrue(PropPhone.isValidPhone("87654321"));
        assertTrue(PropPhone.isValidPhone("61234578"));
    }

    @Test
    public void equals() {
        PropPhone phone = new PropPhone("98765432");

        // same values -> returns true
        assertTrue(phone.equals(new PropPhone("98765432")));

        // same object -> returns true
        assertTrue(phone.equals(phone));

        // null -> returns false
        assertFalse(phone.equals(null));

        // different types -> returns false
        assertFalse(phone.equals(5.0f));

        // different values -> returns false
        assertFalse(phone.equals(new PropPhone("91234567")));
    }
}
