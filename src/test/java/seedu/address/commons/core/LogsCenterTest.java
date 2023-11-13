package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.nio.file.Path;
import java.util.logging.Level;

import org.junit.jupiter.api.Test;

public class LogsCenterTest {
    @Test
    public void init_successfulInit() {
        ConfigStub configStub = new ConfigStub();
        assertDoesNotThrow(() -> LogsCenter.init(configStub));
    }

    /**
     * Stub used to test out the functionality of {@code LogsCenter::init()}.
     */
    private static final class ConfigStub extends Config {
        @Override
        public Level getLogLevel() {
            return super.getLogLevel();
        }

        @Override
        public void setLogLevel(Level logLevel) {
            throw new AssertionError("Should not be called");
        }

        @Override
        public Path getUserPrefsFilePath() {
            throw new AssertionError("Should not be called");
        }

        @Override
        public void setUserPrefsFilePath(Path userPrefsFilePath) {
            throw new AssertionError("Should not be called");
        }

        @Override
        public boolean equals(Object other) {
            throw new AssertionError("Should not be called");
        }

        @Override
        public int hashCode() {
            throw new AssertionError("Should not be called");
        }

        @Override
        public String toString() {
            throw new AssertionError("Should not be called");
        }
    }
}
