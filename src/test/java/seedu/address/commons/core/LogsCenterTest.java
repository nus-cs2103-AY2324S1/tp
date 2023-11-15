package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Level;

import org.junit.jupiter.api.Test;

public class LogsCenterTest {

    @Test
    public void testInit() {
        Config config = new Config();
        config.setLogLevel(Level.INFO);
        LogsCenter.init(config);
        assertEquals(Level.INFO, LogsCenter.getCurrentLogLevel());
    }

}
