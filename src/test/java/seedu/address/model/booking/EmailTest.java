package seedu.address.model.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Email;

public class EmailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Email(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new Email(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> Email.isValidEmail(null));

        // blank email
        assertFalse(Email.isValidEmail("")); // empty string
        assertFalse(Email.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(Email.isValidEmail("@example.com")); // missing local part
        assertFalse(Email.isValidEmail("peterjackexample.com")); // missing '@' symbol
        assertFalse(Email.isValidEmail("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(Email.isValidEmail("peterjack@-")); // invalid domain name
        assertFalse(Email.isValidEmail("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(Email.isValidEmail("peter jack@example.com")); // spaces in local part
        assertFalse(Email.isValidEmail("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(Email.isValidEmail(" peterjack@example.com")); // leading space
        assertFalse(Email.isValidEmail("peterjack@example.com ")); // trailing space
        assertFalse(Email.isValidEmail("peterjack@@example.com")); // double '@' symbol
        assertFalse(Email.isValidEmail("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(Email.isValidEmail("-peterjack@example.com")); // local part starts with a hyphen
        assertFalse(Email.isValidEmail("peterjack-@example.com")); // local part ends with a hyphen
        assertFalse(Email.isValidEmail("peter..jack@example.com")); // local part has two consecutive periods
        assertFalse(Email.isValidEmail("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(Email.isValidEmail("peterjack@.example.com")); // domain name starts with a period
        assertFalse(Email.isValidEmail("peterjack@example.com.")); // domain name ends with a period
        assertFalse(Email.isValidEmail("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(Email.isValidEmail("peterjack@example.com-")); // domain name ends with a hyphen
        assertFalse(Email.isValidEmail("peterjack@example.c")); // top level domain has less than two chars

        // valid email
        assertTrue(Email.isValidEmail("PeterJack_1190@gmail.com")); // underscore in local part
        assertTrue(Email.isValidEmail("PeterJack.1190@gmail.com")); // period in local part
        assertTrue(Email.isValidEmail("PeterJack+1190@gmail.com")); // '+' symbol in local part
        assertTrue(Email.isValidEmail("PeterJack-1190@gmail.com")); // hyphen in local part
        assertTrue(Email.isValidEmail("a@yahoo.com")); // minimal
        assertTrue(Email.isValidEmail("test@icloud.com")); // alphabets only
        assertTrue(Email.isValidEmail("123@gmail.com")); // numeric local part and domain name
        assertTrue(Email.isValidEmail("a1+be.d@hotmail.com")); // mixture of alphanumeric and special characters
    }

    @Test
    public void equals() {
        Email email = new Email("valid@gmail.com");

        // same values -> returns true
        assertTrue(email.equals(new Email("valid@gmail.com")));

        // same object -> returns true
        assertTrue(email.equals(email));

        // null -> returns false
        assertFalse(email.equals(null));

        // different types -> returns false
        assertFalse(email.equals(5.0f));

        // different values -> returns false
        assertFalse(email.equals(new Email("other.valid@gmail.com")));
    }

    @Test
    public void testTruncatedEmailWithinLimit() {
        // Test with an email address having a local-part within the 15-character limit
        Email email = new Email("short@gmail.com");
        assertEquals("short@gmail.com", email.truncatedEmail());
    }

    @Test
    public void testTruncatedEmailExceedingLimit() {
        // Test with an email address having a local-part exceeding the 15-character limit
        Email email = new Email("verylonglocalpart@gmail.com");
        assertEquals("verylongloca...@gmail.com", email.truncatedEmail());
    }

    @Test
    public void testExceedingMaxEmailLength() {
        // Test with an email address local-part exceeding the maximum allowed length
        String longLocalPart = "thisisaverylonglocalpartthatiswaybeyondthelimitforemailaddresses";
        String email = longLocalPart + "@gmail.com";
        assertFalse(Email.isValidEmail(email));
    }
}
