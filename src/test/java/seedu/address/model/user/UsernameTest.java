package seedu.address.model.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UsernameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Username(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidUsername = "";
        assertThrows(IllegalArgumentException.class, () -> new Username(invalidUsername));
    }

    @Test
    public void isValidUsername() {
        // null name
        assertThrows(NullPointerException.class, () -> Username.isValidUsername(null));

        // invalid name
        assertFalse(Username.isValidUsername("")); // empty string
        assertFalse(Username.isValidUsername(" ")); // spaces only
        assertFalse(Username.isValidUsername("\n\r\t")); // escape sequences
        assertFalse(Username.isValidUsername("^")); // only non-alphanumeric characters
        assertFalse(Username.isValidUsername("peter*")); // contains non-alphanumeric characters
        assertFalse(Username.isValidUsername("peter abc")); // contains whitespace

        // valid name
        assertTrue(Username.isValidUsername("peterjack")); // alphabets only
        assertTrue(Username.isValidUsername("12345")); // numbers only
        assertTrue(Username.isValidUsername("imTheBoss")); // alphanumeric characters
        assertTrue(Username.isValidUsername("FOODBEAR")); // with capital letters
        assertTrue(Username.isValidUsername("DavidRogerJacksonRayJr2nd")); // long names
    }

    @Test
    public void equals() {
        Username username = new Username("ValidName");

        // same values -> returns true
        assertTrue(username.equals(new Username("ValidName")));

        // same object -> returns true
        assertTrue(username.equals(username));

        // null -> returns false
        assertFalse(username.equals(null));

        // different types -> returns false
        assertFalse(username.equals(5.0f));

        // different values -> returns false
        assertFalse(username.equals(new Username("OtherValidName")));
    }
}
