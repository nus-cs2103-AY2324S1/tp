package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Paths;

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
        assertThrows(NullPointerException.class, () -> userPrefs.setDeckFilePath(null));
    }

    @Test
    public void equals() {
        UserPrefs userPrefs = new UserPrefs();
        UserPrefs userPref2 = new UserPrefs();
        assertEquals(userPrefs, userPref2);
        userPref2.setDeckFilePath(Paths.get("some.json"));
        assertNotEquals(userPrefs, userPref2);
    }

    @Test
    public void hashcode() {
        UserPrefs userPrefs = new UserPrefs();
        UserPrefs userPref2 = new UserPrefs();
        assertEquals(userPrefs.hashCode(), userPref2.hashCode());
        userPref2.setDeckFilePath(Paths.get("some.json"));
        assertNotEquals(userPrefs.hashCode(), userPref2.hashCode());
    }


}
