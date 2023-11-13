package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void equalsMethod() {
        GuiSettings guiSettings = new GuiSettings();
        assertEquals(guiSettings, guiSettings);
    }

    @Test
    public void equalsMethodNull() {
        GuiSettings guiSettings = new GuiSettings();
        assertEquals(false, guiSettings.equals(null));
    }

    @Test
    public void hashCodeMethod() {
        GuiSettings guiSettings = new GuiSettings();
        assertEquals(guiSettings.hashCode(), guiSettings.hashCode());
    }

}
