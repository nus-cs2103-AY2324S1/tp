package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TelegramNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TelegramName(null));
    }

    @Test
    public void constructor_invalidTelegramName_throwsIllegalArgumentException() {
        String invalidTelegramName = "_";
        assertThrows(IllegalArgumentException.class, () -> new TelegramName(invalidTelegramName));
    }

    @Test
    public void isValidTelegramName() {
        // null telegram name
        assertThrows(NullPointerException.class, () -> TelegramName.isValidTelegramName(null));

        // invalid telegram name
        assertFalse(TelegramName.isValidTelegramName(" ")); // spaces only
        assertFalse(TelegramName.isValidTelegramName("@telegram name with spaces")); // spaces within the name
        assertFalse(TelegramName.isValidTelegramName("@very_long_telegram_name_that_is_invalid")); // too long

        // valid telegram name
        assertTrue(TelegramName.isValidTelegramName("")); // empty string
        assertTrue(TelegramName.isValidTelegramName("@telegram_username")); // lowercase alphabets and underscores
        assertTrue(TelegramName.isValidTelegramName("@telegramUser123")); // mixed alphabets and numbers
        assertTrue(TelegramName.isValidTelegramName("@John_Doe_42")); // underscores and numbers
    }

    @Test
    public void equals() {
        TelegramName telegramName = new TelegramName("@telegram_username");

        // same values -> returns true
        assertTrue(telegramName.equals(new TelegramName("@telegram_username")));

        // same object -> returns true
        assertTrue(telegramName.equals(telegramName));

        // null -> returns false
        assertFalse(telegramName.equals(null));

        // different types -> returns false
        assertFalse(telegramName.equals(5.0f));

        // different values -> returns false
        assertFalse(telegramName.equals(new TelegramName("@other_username")));
    }
}
