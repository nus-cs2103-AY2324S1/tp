package seedu.application.commons.core;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import seedu.application.model.job.Industry;


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
        GuiSettings defaultGuiSetting = new GuiSettings(1,2,3,4);
        assertTrue(defaultGuiSetting.equals(defaultGuiSetting));
        assertFalse(defaultGuiSetting.equals(new Industry("Manufacturing")));
    }
}
