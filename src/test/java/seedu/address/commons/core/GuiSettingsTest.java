package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
    public void testEqualsWithNull() {
        GuiSettings settings = new GuiSettings();
        assertFalse(settings.equals(null));
    }

    @Test
    public void testHashCode() {
        GuiSettings settings1 = new GuiSettings();
        GuiSettings settings2 = new GuiSettings();

        int hashCode1 = settings1.hashCode();
        int hashCode2 = settings2.hashCode();

        assertEquals(hashCode1, hashCode2, "Hash codes should be equal for equal objects");
    }
}
