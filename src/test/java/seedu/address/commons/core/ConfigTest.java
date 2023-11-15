package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ConfigTest {

    @Test
    public void toStringMethod() {
        Config config = new Config();
        String expected = Config.class.getCanonicalName() + "{logLevel=" + config.getLogLevel()
                + ", userPrefsFilePath=" + config.getUserPrefsFilePath() + "}";
        assertEquals(expected, config.toString());
    }

    @Test
    public void equalsMethod() {
        Config defaultConfig = new Config();
        assertNotNull(defaultConfig);
        assertTrue(defaultConfig.equals(defaultConfig));
    }

    @Test
    public void testEqualsWithNull() {
        Config config1 = new Config();
        Config config2 = null;
        assertFalse(config1.equals(config2));
    }

    @Test
    public void testHashCode() {
        Config config1 = new Config();
        Config config2 = new Config();

        int hashCode1 = config1.hashCode();
        int hashCode2 = config2.hashCode();

        assertEquals(hashCode1, hashCode2, "Hash codes should be equal for equal objects");
    }



}
