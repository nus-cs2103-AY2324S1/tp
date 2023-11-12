package swe.context.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;



public class GuiSettingsTest {
    @Test
    public void toStringMethod() {
        GuiSettings guiSettings = new GuiSettings();
        String expected = GuiSettings.class.getCanonicalName() + "{windowWidth=" + guiSettings.getWindowWidth()
                + ", windowHeight=" + guiSettings.getWindowHeight() + ", windowCoordinates="
                + guiSettings.getWindowCoordinates() + "}";
        assertEquals(expected, guiSettings.toString());
    }

    @Test
    public void equals() {
        GuiSettings guiSettings = new GuiSettings();

        // same object -> returns true
        assertTrue(guiSettings.equals(guiSettings));

        // different type -> returns false
        assertFalse(guiSettings.equals(1));

        // null -> returns false
        assertFalse(guiSettings.equals(null));

    }
}
