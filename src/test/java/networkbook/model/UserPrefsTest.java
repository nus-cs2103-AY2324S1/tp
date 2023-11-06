package networkbook.model;

import static networkbook.testutil.Assert.assertThrowsAssertionError;

import org.junit.jupiter.api.Test;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsAssertionError() {
        UserPrefs userPref = new UserPrefs();
        assertThrowsAssertionError(() -> userPref.setGuiSettings(null));
    }

    @Test
    public void setNetworkBookFilePath_nullPath_throwsAssertionError() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrowsAssertionError(() -> userPrefs.setNetworkBookFilePath(null));
    }

}
