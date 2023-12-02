package seedu.address.model.authentication;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AuthenticationDataTest {

    private String validUsername = "username";
    private String validPassword = "password";
    private String validSecretQuestion = "Your first pet's name?";
    private String validAnswer = "koko";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AuthenticationData(
                null, null, null, null));
    }

    @Test
    public void constructor_invalidUsername_throwsIllegalArgumentException() {
        String invalidUsername = null;
        assertThrows(NullPointerException.class, () -> new AuthenticationData(
                invalidUsername, validPassword, validSecretQuestion, validAnswer));
    }

    @Test
    public void constructor_invalidPassword_throwsIllegalArgumentException() {
        String invalidPassword = null;
        assertThrows(NullPointerException.class, () -> new AuthenticationData(
                validUsername, invalidPassword, validSecretQuestion, validAnswer));
    }

    @Test
    public void constructor_invalidSecretQuestion_throwsIllegalArgumentException() {
        String invalidSecretQuestion = null;
        assertThrows(NullPointerException.class, () -> new AuthenticationData(
                validUsername, validPassword, invalidSecretQuestion, validAnswer));
    }

    @Test
    public void constructor_invalidAnswer_throwsIllegalArgumentException() {
        String invalidAnswer = null;
        assertThrows(NullPointerException.class, () -> new AuthenticationData(
                validUsername, validPassword, validSecretQuestion, invalidAnswer));
    }

    @Test
    public void constructor_success() {
        // no exception thrown
        new AuthenticationData(validUsername, validPassword, validSecretQuestion, validAnswer);
    }
}
