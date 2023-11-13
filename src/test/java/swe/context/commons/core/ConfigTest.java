package swe.context.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ConfigTest {

    @Test
    public void toStringMethod() {
        Config config = new Config();
        String expected = Config.class.getCanonicalName()
                + "{settingsPath=" + config.getSettingsPath()
                + ", logLevel=" + config.getLogLevel() + "}";
        assertEquals(expected, config.toString());
    }

    @Test
    public void equals() {
        Config config = new Config();

        // same object -> returns true
        assertTrue(config.equals(config));

        // different type -> returns false
        assertFalse(config.equals(1));

        // null -> returns false
        assertFalse(config.equals(null));
    }
}
