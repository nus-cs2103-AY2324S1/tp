package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GuiSettingsTest {
    @Test
    public void hashCode_sameObject_equals() {
        GuiSettings guiSettings = new GuiSettings();
        assertEquals(guiSettings.hashCode(), guiSettings.hashCode());
    }
    @Test
    public void equals_nullInput_failure() {
        GuiSettings guiSettings = new GuiSettings();
        assertFalse(guiSettings.equals(null));
    }
    @Test
    public void equals_similarObjects_success() {
        GuiSettings guiSettings1 = new GuiSettings();
        GuiSettings guiSettings2 = new GuiSettings();
        assertTrue(guiSettings1.equals(guiSettings2));
    }
    @Test
    public void toStringMethod() {
        GuiSettings guiSettings = new GuiSettings();
        String expected = GuiSettings.class.getCanonicalName() + "{windowWidth=" + guiSettings.getWindowWidth()
                + ", windowHeight=" + guiSettings.getWindowHeight() + ", windowCoordinates="
                + guiSettings.getWindowCoordinates() + "}";
        assertEquals(expected, guiSettings.toString());
    }
}
