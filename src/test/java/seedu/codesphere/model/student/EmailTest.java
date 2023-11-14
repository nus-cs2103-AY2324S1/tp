package seedu.codesphere.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.codesphere.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

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
        assertFalse(Email.isValidEmail("@u.nus.edu")); // missing local part
        assertFalse(Email.isValidEmail("e0123456u.nus.edu")); // missing '@' symbol
        assertFalse(Email.isValidEmail("e0123456@")); // missing domain name

        // invalid parts
        assertFalse(Email.isValidEmail("e0123456@-")); // invalid domain name
        assertFalse(Email.isValidEmail("e0123456@u_nus.edu")); // underscore in domain name
        assertFalse(Email.isValidEmail("e0123 456@u.nus.edu")); // spaces in local part
        assertFalse(Email.isValidEmail("e0123456@u nus.edu")); // spaces in domain name
        assertFalse(Email.isValidEmail(" e0123456@u.nus.edu")); // leading space
        assertFalse(Email.isValidEmail("e0123456@u.nus.edu ")); // trailing space
        assertFalse(Email.isValidEmail("e0123456@@u.nus.edu")); // double '@' symbol
        assertFalse(Email.isValidEmail("e0123@456@u.nus.edu")); // '@' symbol in local part
        assertFalse(Email.isValidEmail("-e012345@u.nus.edu")); // local part starts with a hyphen
        assertFalse(Email.isValidEmail("e012345-@u.nus.edu")); // local part ends with a hyphen
        assertFalse(Email.isValidEmail("e0123456@u.nus@edu")); // '@' symbol in domain name
        assertFalse(Email.isValidEmail("e0123456@.nus.edu")); // domain name starts with a period
        assertFalse(Email.isValidEmail("e0123456@u.nus.edu.")); // domain name ends with a period
        assertFalse(Email.isValidEmail("e0123456@-u.nus.edu")); // domain name starts with a hyphen
        assertFalse(Email.isValidEmail("e0123456@u.nus.edu-")); // domain name ends with a hyphen
        assertFalse(Email.isValidEmail("e0123456@u.nus.e")); // top level domain has less than two chars

        // valid emails
        assertTrue(Email.isValidEmail("e1234567@u.nus.edu"));
        assertTrue(Email.isValidEmail("e1111111@u.nus.edu"));
    }

    @Test
    public void equals() {
        Email email = new Email("e0123456@u.nus.edu");

        // same values -> returns true
        assertTrue(email.equals(new Email("e0123456@u.nus.edu")));

        // same object -> returns true
        assertTrue(email.equals(email));

        // null -> returns false
        assertFalse(email.equals(null));

        // different types -> returns false
        assertFalse(email.equals(5.0f));

        // different values -> returns false
        assertFalse(email.equals(new Email("e0112233@u.nus.edu")));
    }
}
