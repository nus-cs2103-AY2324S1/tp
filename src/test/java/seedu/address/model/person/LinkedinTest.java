package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LinkedinTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Linkedin(null));
    }

    @Test
    public void constructor_invalidLinkedin_throwsIllegalArgumentException() {
        String invalidLinkedin = "";
        assertThrows(IllegalArgumentException.class, () -> new Linkedin(invalidLinkedin));
    }

    @Test
    public void isValidLinkedin() {
        // null linkedin
        assertThrows(NullPointerException.class, () -> Linkedin.isValidLinkedin(null));

        // invalid linkedin
        assertFalse(Linkedin.isValidLinkedin("")); // empty string
        assertFalse(Linkedin.isValidLinkedin(" ")); // spaces only
        assertFalse(Linkedin.isValidLinkedin("?")); // weird linkedin

        // valid linkedin
        assertTrue(Linkedin.isValidLinkedin("johndoe"));
        assertTrue(Linkedin.isValidLinkedin("j")); // one character
        assertTrue(Linkedin.isValidLinkedin("john-doe")); // with hyphens
        assertTrue(Linkedin.isValidLinkedin("john-doe12")); // alphanumeric with hyphens
    }

    @Test
    public void equals() {
        Linkedin linkedin = new Linkedin("ValidLinkedin");

        // same values -> returns true
        assertTrue(linkedin.equals(new Linkedin("ValidLinkedin")));

        // same object -> returns true
        assertTrue(linkedin.equals(linkedin));

        // null -> returns false
        assertFalse(linkedin.equals(null));

        // different types -> returns false
        assertFalse(linkedin.equals(5.0f));

        // different values -> returns false
        assertFalse(linkedin.equals(new Linkedin("OtherValidLinkedin")));
    }
}
