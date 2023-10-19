package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.logging.Level;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.Config;
import seedu.address.commons.exceptions.DataLoadingException;



public class ConfigUtilTest {
    @TempDir
    public static Path TEMP_DIR;

    private static final Path TEST_DATA_FOLDER = Paths.get(
        "src",
        "test",
        "data",
        "ConfigUtilTest"
    );

    //NOTE This should match TypicalConfig.json
    private static Config getTypicalConfig() {
        Config config = new Config();
        config.setSettingsPath(Paths.get("myFolder", "settings.json"));
        config.setLogLevel(Level.INFO);
        return config;
    }

    private static Path getConfigPath(String fileName) {
        return ConfigUtilTest.TEST_DATA_FOLDER.resolve(fileName);
    }

    private Optional<Config> read(String fileName) throws DataLoadingException {
        return ConfigUtil.readConfig(
            ConfigUtilTest.getConfigPath(fileName)
        );
    }

    @Test
    public void readConfig_missingFile_emptyOptionalReturned() throws DataLoadingException {
        assertFalse(this.read("nonExistent.json").isPresent());
    }

    @Test
    public void readConfig_notJson_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> this.read("notJson.json"));
    }

    @Test
    public void readConfig_typical_successfullyRead() throws DataLoadingException {
        Config expected = ConfigUtilTest.getTypicalConfig();
        Config actual = this.read("ConfigUtilTest/typicalConfig.json").get();
        assertEquals(expected, actual);
    }

    @Test
    public void readConfig_missingEntries_defaultsUsed() throws DataLoadingException {
        Config expected = new Config();
        Config actual = read("empty.json").get();
        assertEquals(expected, actual);
    }

    @Test
    public void saveConfig() throws DataLoadingException, IOException {
        Path path = ConfigUtilTest.TEMP_DIR.resolve("tempConfig.json");

        Config config = getTypicalConfig();

        // Try writing when the file doesn't exist
        ConfigUtil.saveConfig(config, path);
        Config readBack = ConfigUtil.readConfig(path).get();
        assertEquals(config, readBack);

        config.setLogLevel(Level.FINE);

        // Try saving when the file exists
        ConfigUtil.saveConfig(config, path);
        readBack = ConfigUtil.readConfig(path).get();
        assertEquals(config, readBack);
    }
}
