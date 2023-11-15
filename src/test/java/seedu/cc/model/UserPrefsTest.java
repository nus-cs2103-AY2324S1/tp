package seedu.cc.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.cc.testutil.Assert.assertThrows;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.cc.commons.core.GuiSettings;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setClinicBookFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setClinicBookFilePath(null));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        UserPrefs userPrefsA = new UserPrefs();
        UserPrefs userPrefsB = new UserPrefs();

        assertEquals(userPrefsA, userPrefsB);
    }

    @Test
    public void equals_differentGuiSettings_returnsFalse() {
        UserPrefs userPrefsA = new UserPrefs();
        userPrefsA.setGuiSettings(new GuiSettings(1, 2, 3, 4));

        UserPrefs userPrefsB = new UserPrefs();
        userPrefsB.setGuiSettings(new GuiSettings(5, 6, 7, 8));

        assertNotEquals(userPrefsA, userPrefsB);
    }

    @Test
    public void equals_differentFilePath_returnsFalse() {
        UserPrefs userPrefsA = new UserPrefs();
        userPrefsA.setClinicBookFilePath(Paths.get("pathA"));

        UserPrefs userPrefsB = new UserPrefs();
        userPrefsB.setClinicBookFilePath(Paths.get("pathB"));

        assertNotEquals(userPrefsA, userPrefsB);
    }

    @Test
    public void equals_otherObject_returnsFalse() {
        UserPrefs userPrefs = new UserPrefs();
        String notAUserPrefs = "I am not a UserPrefs object";

        assertNotEquals(userPrefs, notAUserPrefs);
    }

    @Test
    public void hashCode_sameUserPrefs_sameHashCode() {
        UserPrefs userPrefsA = new UserPrefs();
        UserPrefs userPrefsB = new UserPrefs();

        assertEquals(userPrefsA.hashCode(), userPrefsB.hashCode());
    }



}
