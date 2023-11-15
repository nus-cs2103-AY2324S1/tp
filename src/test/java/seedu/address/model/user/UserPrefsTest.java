package seedu.address.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UserPrefsTest {
    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setAddressBookFilePath(null));
    }

    @Test
    public void equals() {
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        assertTrue(userPrefs.equals(new UserPrefs()));

        // same object -> returns true
        assertTrue(userPrefs.equals(userPrefs));

        // null -> returns false
        assertFalse(userPrefs.equals(null));

        // different types -> returns false
        assertFalse(userPrefs.equals(5.0f));

    }

    @Test
    public void testHashCode() {
        UserPrefs userPrefs = new UserPrefs();
        assertEquals(userPrefs.hashCode(), new UserPrefs().hashCode());
    }

    @Test
    public void testToString() {
        UserPrefs userPrefs = new UserPrefs();
        String expected = "Gui Settings : " + userPrefs.getGuiSettings()
                + "\nLocal data file location : " + userPrefs.getAddressBookFilePath();
        assertEquals(expected, userPrefs.toString());
    }

}
