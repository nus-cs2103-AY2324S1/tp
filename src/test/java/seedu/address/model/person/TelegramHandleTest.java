package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TelegramHandleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TelegramHandle(null));
    }

    @Test
    public void isValidTelegramHandle() {
        // invalid telegram handles
        assertFalse(TelegramHandle.isValidTelegramHandle("")); // empty string
        assertFalse(TelegramHandle.isValidTelegramHandle(" ")); // spaces only
        assertFalse(TelegramHandle.isValidTelegramHandle("@")); // only one character
        assertFalse(TelegramHandle.isValidTelegramHandle("@ ")); // space after "@"
        assertFalse(TelegramHandle.isValidTelegramHandle("testHandle")); // missing "@"

        // valid telegram handles
        assertTrue(TelegramHandle.isValidTelegramHandle("@testHandle")); // alphanumeric characters
        assertTrue(TelegramHandle.isValidTelegramHandle("@test Handle")); // alphanumeric characters with space
    }

    @Test
    public void equals() {
        TelegramHandle handle = new TelegramHandle("@testHandle");

        // same values -> returns true
        assertTrue(handle.equals(new TelegramHandle("@testHandle")));

        // same object -> returns true
        assertTrue(handle.equals(handle));

        // null -> returns false
        assertFalse(handle.equals(null));

        // different types -> returns false
        assertFalse(handle.equals(5.0f));

        // different values -> returns false
        assertFalse(handle.equals(new TelegramHandle("@anotherTestHandle")));
    }
}
