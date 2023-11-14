package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TelegramTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Telegram(null));
    }

    @Test
    public void constructor_invalidTelegram_throwsIllegalArgumentException() {
        String invalidTelegram = "";
        assertThrows(IllegalArgumentException.class, () -> new Telegram(invalidTelegram));
    }

    @Test
    public void isValidTelegram() {
        // null telegram handle
        assertThrows(NullPointerException.class, () -> Telegram.isValidTelegram(null));

        // invalid telegram handle
        assertFalse(Telegram.isValidTelegram("")); // empty string
        assertFalse(Telegram.isValidTelegram(" ")); // spaces only
        assertFalse(Telegram.isValidTelegram("@heymumble_jumbo123blahblahhellohellotestme")); // long address
        assertFalse(Telegram.isValidTelegram("@s")); // one character

        // valid telegram handle
        assertTrue(Telegram.isValidTelegram("@heyanything"));
    }

    @Test
    public void equals() {
        Telegram telegram = new Telegram("@helloitstele");

        // same values -> returns true
        assertEquals(telegram, new Telegram("@helloitstele"));

        // same object -> returns true
        assertEquals(telegram, telegram);

        // null -> returns false
        assertNotEquals(null, telegram);

        // different types -> returns false
        assertNotEquals(5.0f, telegram, "0.0");

        // different values -> returns false
        assertNotEquals(telegram, new Telegram("@todayisnice"));
    }
}
