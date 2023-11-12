package swe.context.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import swe.context.testutil.TestData;


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

    @Test
    public void equals() {
        Phone amy = new Phone(TestData.Valid.PHONE_AMY);

        // same values -> return true
        Phone amyCopy = new Phone(TestData.Valid.PHONE_AMY);
        assertTrue(amy.equals(amyCopy));

        // same object -> return true
        assertTrue(amy.equals(amy));

        // different type -> return false
        assertFalse(amy.equals(1));

        // null -> return false
        assertFalse(amy.equals(null));

        // different phone -> return false
        Phone bob = new Phone(TestData.Valid.PHONE_BOB);
        assertFalse(amy.equals(bob));
    }
}
