package seedu.address.model;

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
    public void setManageHrFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setManageHrFilePath(null));
    }

    @Test
    public void equals_sameObj_success() {
        UserPrefs userPrefs = new UserPrefs();
        assertTrue(userPrefs.equals(new UserPrefs()));
    }

    @Test
    public void equals_null_failure() {
        UserPrefs userPrefs = new UserPrefs();
        assertFalse(userPrefs.equals(null));
    }

    @Test
    public void getHash_sameObj_success() {
        UserPrefs userPrefs = new UserPrefs();
        assertEquals(userPrefs.hashCode(), new UserPrefs().hashCode());
    }
}
