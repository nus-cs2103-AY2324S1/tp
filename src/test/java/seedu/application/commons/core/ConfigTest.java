package seedu.application.commons.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import seedu.application.model.job.Industry;


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
        assertFalse(defaultConfig.equals(new Industry("Manufacturing")));
    }


}
