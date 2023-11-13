package seedu.staffsnap.commons.core;

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
        GuiSettings defaultGuiSettings = new GuiSettings();
        assertEquals(defaultGuiSettings, defaultGuiSettings);
    }

    @Test
    public void hashCodeMethod() {
        GuiSettings defaultGuiSettings = new GuiSettings();
        assertEquals(defaultGuiSettings.hashCode(), defaultGuiSettings.hashCode());
    }
}
