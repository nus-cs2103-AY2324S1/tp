package seedu.application.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setApplicationBookFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setApplicationBookFilePath(null));
    }

    @Test
    void testEquals() {
        UserPrefs userPref = new UserPrefs();
        // same object -> returns true
        assertTrue(userPref.equals(userPref));

        // null -> returns false
        assertFalse(userPref.equals(null));
    }

}
