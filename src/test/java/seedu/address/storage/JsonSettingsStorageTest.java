package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlySettings;
import seedu.address.model.Settings;
import seedu.address.testutil.TestData;



public class JsonSettingsStorageTest {
    @TempDir
    public static Path TEMP_DIR;

    private static final Path TEST_DATA_FOLDER = Paths.get(
        "src",
        "test",
        "data",
        "JsonSettingsStorageTest"
    );

    private Optional<? extends ReadOnlySettings> read(String fileName) throws DataLoadingException {
        return new JsonSettingsStorage(
            JsonSettingsStorageTest.TEST_DATA_FOLDER.resolve(fileName)
        ).readSettings();
    }

    @Test
    public void readSettings_typical_successfullyRead() throws DataLoadingException {
        ReadOnlySettings expected = TestData.getTypicalSettings();
        ReadOnlySettings actual = this.read("typicalSettings.json").get();
        assertEquals(expected, actual);
    }

    @Test
    public void readSettings_missingEntries_defaultsUsed() throws DataLoadingException {
        ReadOnlySettings expected = new Settings();
        ReadOnlySettings actual = this.read("../empty.json").get();
        assertEquals(expected, actual);
    }

    @Test
    public void saveSettings() throws DataLoadingException, IOException {
        Path tempPath = JsonSettingsStorageTest.TEMP_DIR.resolve("tempSettings.json");

        Settings settings = new Settings();
        settings.setGuiSettings(new GuiSettings(1200, 200, 0, 2));

        SettingsStorage storage = new JsonSettingsStorage(tempPath);

        // Try writing when the file doesn't exist
        storage.saveSettings(settings);
        ReadOnlySettings readBack = storage.readSettings().get();
        assertEquals(settings, readBack);

        settings.setContactsPath(Paths.get("unusedPath", "noFilesActuallyChanged.json"));

        // Try saving when the file exists
        storage.saveSettings(settings);
        readBack = storage.readSettings().get();
        assertEquals(settings, readBack);
    }
}
