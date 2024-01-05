package seedu.address.model.person.assignment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.TelegramHandle;

public class TelegramHandleTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TelegramHandle(null));
    }

    @Test
    public void constructor_invalidTelegramHandle_throwsIllegalArgumentException() {
        String invalidTelegramHandle = "";
        assertThrows(IllegalArgumentException.class, () -> new TelegramHandle(invalidTelegramHandle));
    }

    @Test
    public void isValidTelegramHandle() {
        // null telegram handle
        assertThrows(NullPointerException.class, () -> TelegramHandle.isValidTelegramHandle(null));

        // invalid telegram handle
        assertFalse(TelegramHandle.isValidTelegramHandle("")); // empty string
        assertFalse(TelegramHandle.isValidTelegramHandle(" ")); // spaces only
        assertFalse(TelegramHandle.isValidTelegramHandle("@#$%")); // special characters

        // valid telegram handle
        assertTrue(TelegramHandle.isValidTelegramHandle("l_dinghan")); // underscores allowed
        assertTrue(TelegramHandle.isValidTelegramHandle("notwesho1107")); // alphanumeric characters
        assertTrue(TelegramHandle.isValidTelegramHandle("respirayson")); // only alphabetical characters
        assertTrue(TelegramHandle.isValidTelegramHandle("r")); // one character
    }

    @Test
    public void equals() {
        TelegramHandle telegramHandle = new TelegramHandle("ValidTelegramHandle");

        // same values -> returns true
        assertTrue(telegramHandle.equals(new TelegramHandle("ValidTelegramHandle")));

        // same object -> returns true
        assertTrue(telegramHandle.equals(telegramHandle));

        // null -> returns false
        assertFalse(telegramHandle.equals(null));

        // different types -> returns false
        assertFalse(telegramHandle.equals(5.0f));

        // different values -> returns false
        assertFalse(telegramHandle.equals(new TelegramHandle("OtherValidTelegramHandle")));
    }
}
