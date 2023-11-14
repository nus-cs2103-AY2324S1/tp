package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;



public class ConfigTest {

    @Test
    public void equals_nullInput_failure() {
        Config config = new Config();
        assertFalse(config.equals(null));
    }
    @Test
    public void hashCode_sameObject_equals() {
        Config config = new Config();
        assertEquals(config.hashCode(), config.hashCode());
    }
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
    public void equals_similarObjects_success() {
        Config config1 = new Config();
        Config config2 = new Config();
        assertTrue(config1.equals(config2));
    }


}
