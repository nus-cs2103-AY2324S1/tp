package seedu.address.model.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PasswordTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Password(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidPassword = "";
        assertThrows(IllegalArgumentException.class, () -> new Password(invalidPassword));
    }

    @Test
    public void isValidPassword() {
        assertThrows(NullPointerException.class, () -> Password.isValidPassword(null));

        // invalid name
        assertFalse(Password.isValidPassword("")); // empty string
        assertFalse(Password.isValidPassword("abc123")); // less than 8 characters
        assertFalse(Password.isValidPassword("        ")); // spaces only
        assertFalse(Password.isValidPassword("\n\r\t")); // escape sequences
        assertFalse(Password.isValidPassword("^&%$@#!%*#()")); // only non-alphanumeric characters
        assertFalse(Password.isValidPassword("peter*is*me")); // contains non-alphanumeric characters
        assertFalse(Password.isValidPassword("peter abc")); // contains whitespace

        // valid name
        assertTrue(Password.isValidPassword("peterjack")); // alphabets only
        assertTrue(Password.isValidPassword("12345678")); // numbers only
        assertTrue(Password.isValidPassword("imTheBoss")); // alphanumeric characters
        assertTrue(Password.isValidPassword("FOODBEAR")); // with capital letters
        assertTrue(Password.isValidPassword("DavidRogerJacksonRayJr2nd")); // long password
    }

    @Test
    public void equals() {
        Password password = new Password("ValidPa55word");

        // same values -> returns true
        assertTrue(password.equals(new Password("ValidPa55word")));

        // same object -> returns true
        assertTrue(password.equals(password));

        // null -> returns false
        assertFalse(password.equals(null));

        // different types -> returns false
        assertFalse(password.equals(5.0f));

        // different values -> returns false
        assertFalse(password.equals(new Password("ValidPassword")));
    }

    @Test
    public void toStringTest() {
        // password is "password"
        Password password = new Password("5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8", true);
        assertTrue(password.toString().equals("5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8"));
    }

    @Test
    public void hashCode_sameHashCode_returnsTrue() {
        Password password = new Password("ValidPa55word");
        assertTrue(password.hashCode() == password.hashCode());
    }

    @Test
    public void hashCode_differentHashCode_returnsFalse() {
        Password password = new Password("ValidPa55word");
        Password differentPassword = new Password("DifferentPa55word");
        assertFalse(password.hashCode() == differentPassword.hashCode());
    }

}
