package seedu.ccacommander.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.ccacommander.ui.Stylesheet.constructStylesheet;

import org.junit.jupiter.api.Test;
class StylesheetTest {
    @Test
    public void lightThemeExists() {
        assertNotNull(Stylesheet.LIGHT.getStylesheet());
    }
    @Test
    public void darkThemeExists() {
        assertNotNull(Stylesheet.DARK.getStylesheet());
    }
    @Test
    public void extensionsExists() {
        assertNotNull(Stylesheet.EXTENSION.getStylesheet());
    }

    @Test
    public void testConstructStyleSheet() {
        assertEquals(Stylesheet.LIGHT, constructStylesheet("LightTheme.css"));
        assertEquals(Stylesheet.DARK, constructStylesheet("DarkTheme.css"));
        assertEquals(Stylesheet.DEFAULT_STYLESHEET, constructStylesheet("LightTheme.css"));
        assertEquals(Stylesheet.DEFAULT_STYLESHEET, constructStylesheet("INVALID_INPUT"));
    }
}
