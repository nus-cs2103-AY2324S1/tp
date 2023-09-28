package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NextOfKinPhoneTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NextOfKinPhone(null));
    }

    @Test
    public void constructor_invalidNextOfKinPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new NextOfKinPhone(invalidPhone));
    }

    @Test
    public void isValidNextOfKinPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> NextOfKinPhone.isValidPhone(null));

        // invalid phone numbers
        assertFalse(NextOfKinPhone.isValidPhone("")); // empty string
        assertFalse(NextOfKinPhone.isValidPhone(" ")); // spaces only
        assertFalse(NextOfKinPhone.isValidPhone("91")); // less than 3 numbers
        assertFalse(NextOfKinPhone.isValidPhone("phone")); // non-numeric
        assertFalse(NextOfKinPhone.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(NextOfKinPhone.isValidPhone("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(NextOfKinPhone.isValidPhone("911")); // exactly 3 numbers
        assertTrue(NextOfKinPhone.isValidPhone("93121534"));
        assertTrue(NextOfKinPhone.isValidPhone("124293842033123")); // long phone numbers
    }

    @Test
    public void equals() {
        NextOfKinPhone phone = new NextOfKinPhone("999");

        // same values -> returns true
        assertTrue(phone.equals(new NextOfKinPhone("999")));

        // same object -> returns true
        assertTrue(phone.equals(phone));

        // null -> returns false
        assertFalse(phone.equals(null));

        // different types -> returns false
        assertFalse(phone.equals(5.0f));

        // different values -> returns false
        assertFalse(phone.equals(new NextOfKinPhone("995")));
    }
}
