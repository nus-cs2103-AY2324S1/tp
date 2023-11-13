package seedu.lovebook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.lovebook.testutil.Assert.assertThrows;
import static seedu.lovebook.testutil.TypicalDatePrefs.getTypicalDatePrefs;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.lovebook.commons.exceptions.DataLoadingException;
import seedu.lovebook.model.DatePrefs;
import seedu.lovebook.model.ReadOnlyDatePrefs;

public class JsonDatePrefsStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonDatePrefsStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readDatePrefs_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readDatePrefs(null));
    }

    private java.util.Optional<ReadOnlyDatePrefs> readDatePrefs(String filePath) throws Exception {
        return new JsonDatePrefsStorage(Paths.get(filePath)).readDatePrefs(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readDatePrefs("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readDatePrefs("notJsonFormatDatePrefs.json"));
    }

    @Test
    public void readDatePrefs_invalidDatePrefs_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readDatePrefs("invalidDatePrefs.json"));
    }

    @Test
    public void readAndSaveDatePrefs_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempLoveBook.json");
        DatePrefs original = getTypicalDatePrefs();
        JsonDatePrefsStorage jsonDatePrefsStorage = new JsonDatePrefsStorage(filePath);

        // Save in new file and read back
        jsonDatePrefsStorage.saveDatePrefs(original, filePath);
        ReadOnlyDatePrefs readBack = jsonDatePrefsStorage.readDatePrefs(filePath).get();
        assertEquals(original, new DatePrefs(readBack));

        // Modify data, overwrite exiting file, and read back
        original.setPreferences(getTypicalDatePrefs());
        jsonDatePrefsStorage.saveDatePrefs(original, filePath);
        readBack = jsonDatePrefsStorage.readDatePrefs(filePath).get();
        assertEquals(original, new DatePrefs(readBack));

        // Save and read without specifying file path
        jsonDatePrefsStorage.saveDatePrefs(original); // file path not specified
        readBack = jsonDatePrefsStorage.readDatePrefs().get(); // file path not specified
        assertEquals(original, new DatePrefs(readBack));

    }

    @Test
    public void saveDatePrefs_nullDatePrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveDatePrefs(null, "SomeFile.json"));
    }

    /**
     * Saves {@code LoveBook} at the specified {@code filePath}.
     */
    private void saveDatePrefs(ReadOnlyDatePrefs datePrefs, String filePath) {
        try {
            new JsonDatePrefsStorage(Paths.get(filePath))
                    .saveDatePrefs(datePrefs, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveDatePrefs_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveDatePrefs(new DatePrefs(), null));
    }
}
