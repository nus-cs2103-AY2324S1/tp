package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

public class LogsCenterTest {
    @Test
    public void initMethod() {
        Config config = new Config();
        LogsCenter.init(config);
    }

    @Test
    public void getLoggerMethod() {
        String name = "name";
        Logger logger = LogsCenter.getLogger(name);
        assertNotNull(logger);
    }

    @Test
    public void getLoggerMethodClass() {
        Class<?> clazz = LogsCenter.class;
        Logger logger = LogsCenter.getLogger(clazz);
        assertNotNull(logger);
    }
}
