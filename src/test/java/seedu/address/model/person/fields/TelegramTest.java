package seedu.address.model.person.fields;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
        // null telegram
        assertThrows(NullPointerException.class, () -> Telegram.isValidHandle(null));

        // invalid telegram
        assertFalse(Telegram.isValidHandle("")); // empty string
        assertFalse(Telegram.isValidHandle(" ")); // spaces only
        assertFalse(Telegram.isValidHandle("@pete")); // less than 5 characters
        assertFalse(Telegram.isValidHandle("@abcdeabcdeabcdeabcdeabcdeabcde123")); // more than 32 characters
        assertFalse(Telegram.isValidHandle("petejack")); // missing '@' symbol
        assertFalse(Telegram.isValidHandle("@pete jack")); // spaces in telegram

        // valid telegram
        assertTrue(Telegram.isValidHandle("@peterjack_1190")); // underscore in telegram
        assertTrue(Telegram.isValidHandle("@peterjack1190"));
        assertTrue(Telegram.isValidHandle("@PeterJack1190")); // capital letters in telegram
    }

    @Test
    public void equals() {
        Telegram telegram = new Telegram("@peterjack_1190");

        // same values -> returns true
        assertTrue(telegram.equals(new Telegram("@peterjack_1190")));

        // same object -> returns true
        assertTrue(telegram.equals(telegram));

        // null -> returns false
        assertFalse(telegram.equals(null));

        // different types -> returns false
        assertFalse(telegram.equals(5.0f));

        // different values -> returns false
        assertFalse(telegram.equals(new Telegram("@peterjack_1191")));
    }
}
