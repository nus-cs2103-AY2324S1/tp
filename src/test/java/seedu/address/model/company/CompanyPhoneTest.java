package seedu.address.model.company;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CompanyPhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CompanyPhone(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new CompanyPhone(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> CompanyPhone.isValidPhone(null));

        // invalid phone numbers
        assertFalse(CompanyPhone.isValidPhone("")); // empty string
        assertFalse(CompanyPhone.isValidPhone(" ")); // spaces only
        assertFalse(CompanyPhone.isValidPhone("91")); // less than 3 numbers
        assertFalse(CompanyPhone.isValidPhone("phone")); // non-numeric
        assertFalse(CompanyPhone.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(CompanyPhone.isValidPhone("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(CompanyPhone.isValidPhone("911")); // exactly 3 numbers
        assertTrue(CompanyPhone.isValidPhone("93121534"));
        assertTrue(CompanyPhone.isValidPhone("124293842033123")); // long phone numbers
    }

    @Test
    public void equals() {
        CompanyPhone phone = new CompanyPhone("999");

        // same values -> returns true
        assertTrue(phone.equals(new CompanyPhone("999")));

        // same object -> returns true
        assertTrue(phone.equals(phone));

        // null -> returns false
        assertFalse(phone.equals(null));

        // different types -> returns false
        assertFalse(phone.equals(5.0f));

        // different values -> returns false
        assertFalse(phone.equals(new CompanyPhone("995")));
    }
}
