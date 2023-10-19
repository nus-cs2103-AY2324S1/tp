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
import seedu.address.testutil.TestData;



public class ConfigUtilTest {
    @TempDir
    public static Path TEMP_DIR;

    private static final Path TEST_DATA_FOLDER = Paths.get(
        "src",
        "test",
        "data"
    );

    private Optional<Config> read(String fileName) throws DataLoadingException {
        return ConfigUtil.readConfig(
            ConfigUtilTest.TEST_DATA_FOLDER.resolve(fileName)
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
        Config expected = TestData.getTypicalConfig();
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
        Path tempPath = ConfigUtilTest.TEMP_DIR.resolve("tempConfig.json");

        Config config = TestData.getTypicalConfig();

        // Try writing when the file doesn't exist
        ConfigUtil.saveConfig(config, tempPath);
        Config readBack = ConfigUtil.readConfig(tempPath).get();
        assertEquals(config, readBack);

        config.setLogLevel(Level.FINE);

        // Try saving when the file exists
        ConfigUtil.saveConfig(config, tempPath);
        readBack = ConfigUtil.readConfig(tempPath).get();
        assertEquals(config, readBack);
    }
}
