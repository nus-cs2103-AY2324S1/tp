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
import seedu.address.model.AnimalUserPrefs;

public class JsonAnimalUserPrefsStorageTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAnimalUserPrefsStorageTest");
    private static final String EMPTY_PREFS_FILE_PATH = "EmptyUserPrefs.json";
    private static final String EXTRA_VALUES_USER_PREFS_FILE_PATH = "ExtraValuesUserPref.json";
    private static final String NOT_JSON_FORMAT_FILE_PATH = "NotJsonFormatUserPrefs.json";
    private static final String TYPICAL_USER_PREFS_FILE_PATH = "TypicalUserPref.json";

    @TempDir
    public Path testFolder;

    @Test
    public void readUserPrefs_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readUserPrefs(null));
    }

    private Optional<AnimalUserPrefs> readUserPrefs(String userPrefsFileInTestDataFolder) throws DataLoadingException {
        Path prefsFilePath = addToTestDataPathIfNotNull(userPrefsFileInTestDataFolder);
        return new JsonAnimalUserPrefsStorage(prefsFilePath).readAnimalUserPrefs(prefsFilePath);
    }

    @Test
    public void readUserPrefs_missingFile_emptyResult() throws DataLoadingException {
        assertFalse(readUserPrefs("NonExistentFile.json").isPresent());
    }

    @Test
    public void readUserPrefs_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readUserPrefs(NOT_JSON_FORMAT_FILE_PATH));
    }

    private Path addToTestDataPathIfNotNull(String userPrefsFileInTestDataFolder) {
        return userPrefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(userPrefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void readUserPrefs_fileInOrder_successfullyRead() throws DataLoadingException {
        AnimalUserPrefs expected = getTypicalUserPrefs();
        AnimalUserPrefs actual = readUserPrefs(TYPICAL_USER_PREFS_FILE_PATH).get();
        assertEquals(expected, actual);
    }

    @Test
    public void readUserPrefs_valuesMissingFromFile_defaultValuesUsed() throws DataLoadingException {
        AnimalUserPrefs actual = readUserPrefs(EMPTY_PREFS_FILE_PATH).get();
        assertEquals(new AnimalUserPrefs(), actual);
    }

    @Test
    public void readUserPrefs_extraValuesInFile_extraValuesIgnored() throws DataLoadingException {
        AnimalUserPrefs expected = getTypicalUserPrefs();
        AnimalUserPrefs actual = readUserPrefs(EXTRA_VALUES_USER_PREFS_FILE_PATH).get();

        assertEquals(expected, actual);
    }

    private AnimalUserPrefs getTypicalUserPrefs() {
        AnimalUserPrefs userPrefs = new AnimalUserPrefs();
        userPrefs.setGuiSettings(new GuiSettings(1000, 500, 300, 100));
        userPrefs.setAnimalCatalogFilePath(Paths.get("animalcatalog.json"));
        return userPrefs;
    }

    @Test
    public void savePrefs_nullPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveUserPrefs(null, "SomeFile.json"));
    }

    @Test
    public void saveUserPrefs_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveUserPrefs(new AnimalUserPrefs(), null));
    }

    /**
     * Saves {@code userPrefs} at the specified {@code prefsFileInTestDataFolder} filepath.
     */
    private void saveUserPrefs(AnimalUserPrefs userPrefs, String prefsFileInTestDataFolder) {
        try {
            new JsonAnimalUserPrefsStorage(addToTestDataPathIfNotNull(prefsFileInTestDataFolder))
                    .saveAnimalUserPrefs(userPrefs);
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file", ioe);
        }
    }

    @Test
    public void saveUserPrefs_allInOrder_success() throws DataLoadingException, IOException {

        AnimalUserPrefs original = new AnimalUserPrefs();
        original.setGuiSettings(new GuiSettings(1200, 200, 0, 2));

        Path pefsFilePath = testFolder.resolve("TempPrefs.json");
        JsonAnimalUserPrefsStorage jsonUserPrefsStorage = new JsonAnimalUserPrefsStorage(pefsFilePath);

        //Try writing when the file doesn't exist
        jsonUserPrefsStorage.saveAnimalUserPrefs(original);
        AnimalUserPrefs readBack = jsonUserPrefsStorage.readAnimalUserPrefs().get();
        assertEquals(original, readBack);

        //Try saving when the file exists
        original.setGuiSettings(new GuiSettings(5, 5, 5, 5));
        jsonUserPrefsStorage.saveAnimalUserPrefs(original);
        readBack = jsonUserPrefsStorage.readAnimalUserPrefs().get();
        assertEquals(original, readBack);
    }

    @Test
    public void getUserPrefsFilePathTest() {
        JsonAnimalUserPrefsStorage testStorage = new JsonAnimalUserPrefsStorage(TEST_DATA_FOLDER);
        assertEquals(testStorage.getAnimalUserPrefsFilePath(), TEST_DATA_FOLDER);
    }

}
