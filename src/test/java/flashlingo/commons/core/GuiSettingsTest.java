package flashlingo.commons.core;

import org.junit.jupiter.api.Test;
import seedu.flashlingo.commons.core.GuiSettings;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GuiSettingsTest {
    @Test
    public void toStringMethod() {
        GuiSettings guiSettings = new GuiSettings();
        String expected = GuiSettings.class.getCanonicalName() + "{windowWidth=" + guiSettings.getWindowWidth()
                + ", windowHeight=" + guiSettings.getWindowHeight() + ", windowCoordinates="
                + guiSettings.getWindowCoordinates() + "}";
        assertEquals(expected, guiSettings.toString());
    }
}

