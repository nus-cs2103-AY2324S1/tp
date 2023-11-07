package swe.context.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;



public class PhoneTest {
    @Test
    public void isValidPhone() {
        // invalid phone numbers
        assertFalse(Phone.isValid(""));
        assertFalse(Phone.isValid(" "));
        assertFalse(Phone.isValid("91")); // Less than 3 digits
        assertFalse(Phone.isValid("phone")); // Does not start with digits

        // valid phone numbers
        assertTrue(Phone.isValid("911")); // Starts with 3 digits
        assertTrue(Phone.isValid("93121534"));
        assertTrue(Phone.isValid("124293842033123")); // Long phone number
        assertTrue(Phone.isValid("999 (police)")); // Phone number with added text
    }
}
