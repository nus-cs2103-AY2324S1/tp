package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Point;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;

public class GuiSettingsTest {

    final double windowWidth = 100;
    final double windowHeight = 100;
    final int xPosition = 0;
    final int yPosition = 0;
    final String theme = "/view/LightTheme.css";

    @Test
    public void getTheme() {
        GuiSettings guiSettings1 = new GuiSettings();
        assertEquals(guiSettings1.getTheme(), GuiSettings.DEFAULT_THEME);

        GuiSettings guiSettings2 = new GuiSettings(windowWidth, windowHeight, xPosition, yPosition, theme);
        assertEquals(guiSettings2.getTheme(), theme);

        GuiSettings guiSettings3 = new GuiSettings(windowWidth, windowHeight, xPosition, yPosition, null);
        assertEquals(guiSettings3.getTheme(), GuiSettings.DEFAULT_THEME);
    }

    @Test
    public void getWindowCoordinates() {
        GuiSettings guiSettings1 = new GuiSettings();
        assertNull(guiSettings1.getWindowCoordinates());

        GuiSettings guiSettings2 = new GuiSettings(windowWidth, windowHeight, xPosition, yPosition, theme);
        assertEquals(guiSettings2.getWindowCoordinates(), new Point(xPosition, yPosition));
    }

    @Test
    public void hashcode() {
        GuiSettings guiSettings = new GuiSettings();
        GuiSettings otherGuiSettings1 = new GuiSettings();

        GuiSettings otherGuiSettings2 = new GuiSettings(windowWidth, windowHeight, xPosition, yPosition, theme);
        GuiSettings otherGuiSettings3 = new GuiSettings(windowWidth, windowHeight, xPosition, yPosition, theme);

        // different object and different attributes -> different hashcode
        assertNotEquals(guiSettings.hashCode(), otherGuiSettings2.hashCode());

        // same object -> same hashcode
        assertEquals(guiSettings.hashCode(), guiSettings.hashCode());
        // different object but same attributes -> same hashcode
        assertEquals(guiSettings.hashCode(), otherGuiSettings1.hashCode());
        // different object but same attributes -> same hashcode
        assertEquals(otherGuiSettings2.hashCode(), otherGuiSettings3.hashCode());
    }

    @Test
    public void toStringMethod() {
        GuiSettings guiSettings = new GuiSettings();
        String expected = GuiSettings.class.getCanonicalName() + "{windowWidth=" + guiSettings.getWindowWidth()
                + ", windowHeight=" + guiSettings.getWindowHeight() + ", windowCoordinates="
                + guiSettings.getWindowCoordinates() + ", theme=" + guiSettings.getTheme() + "}";
        assertEquals(expected, guiSettings.toString());
    }

    @Test
    public void equals() {
        GuiSettings guiSettings1 = new GuiSettings();
        GuiSettings guiSettings2 = new GuiSettings(windowWidth, windowHeight, xPosition, yPosition, theme);

        GuiSettings otherGuiSettings1 = new GuiSettings();
        GuiSettings otherGuiSettings2 = new GuiSettings(windowWidth, windowHeight, xPosition, yPosition, theme);

        // same object -> returns true
        assertTrue(guiSettings1.equals(guiSettings1));
        assertTrue(guiSettings2.equals(guiSettings2));

        // same values -> returns true
        assertTrue(guiSettings1.equals(otherGuiSettings1));
        assertTrue(guiSettings2.equals(otherGuiSettings2));

        // different types -> returns false
        assertFalse(guiSettings1.equals(new ClearCommand()));

        // null -> returns false
        assertFalse(guiSettings1.equals(null));
        assertFalse(guiSettings2.equals(null));

        // different windowWidth
        assertFalse(guiSettings2.equals(
            new GuiSettings(windowWidth + 1, windowHeight, xPosition, yPosition, theme)));

        // different windowHeight
        assertFalse(guiSettings2.equals(
            new GuiSettings(windowWidth, windowHeight + 1, xPosition, yPosition, theme)));

        // different windowCoordinates
        assertFalse(guiSettings2.equals(
            new GuiSettings(windowWidth, windowHeight, xPosition + 1, yPosition + 1, theme)));

        // different theme
        assertFalse(guiSettings2.equals(
            new GuiSettings(windowWidth, windowHeight, xPosition, yPosition, "other theme")));

        // different values
        assertFalse(guiSettings1.equals(otherGuiSettings2));
        assertFalse(guiSettings2.equals(otherGuiSettings1));
    }
}
