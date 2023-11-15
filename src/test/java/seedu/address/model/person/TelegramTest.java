package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        assertThrows(NullPointerException.class, () -> Telegram.isValidTelegram(null));

        // invalid telegram
        assertFalse(Telegram.isValidTelegram("")); // empty string
        assertFalse(Telegram.isValidTelegram(" ")); // spaces only
        assertFalse(Telegram.isValidTelegram("?")); // invalid special characters
        assertFalse(Telegram.isValidTelegram("johndoe")); // not following telegram format
        assertFalse(Telegram.isValidTelegram("john")); // less than minimum of 5 characters
        assertFalse(Telegram.isValidTelegram("Johndoe")); // no capitalised letters

        // valid telegram
        assertTrue(Telegram.isValidTelegram("@johndoe")); // alphabets
        assertTrue(Telegram.isValidTelegram("@johndoe123")); // alphanumeric
        assertTrue(Telegram.isValidTelegram("@john_doe123")); // alphanumeric with underscores
    }

    @Test
    public void equals() {
        Telegram telegram = new Telegram("@validtelegram");

        // same values -> returns true
        assertTrue(telegram.equals(new Telegram("@validtelegram")));

        // same object -> returns true
        assertTrue(telegram.equals(telegram));

        // null -> returns false
        assertFalse(telegram.equals(null));

        // different types -> returns false
        assertFalse(telegram.equals(5.0f));

        // different values -> returns false
        assertFalse(telegram.equals(new Telegram("@othervalidtelegram")));
    }

    @Test
    public void testToString() {
        assertEquals("@telegram", new Telegram("@telegram").toString());
    }

    @Test
    public void testHashCode() {
        Telegram telegram1 = new Telegram("@telegram");
        Telegram telegram2 = new Telegram("@telegram");
        assertEquals(telegram1.hashCode(), telegram2.hashCode());
    }
}
