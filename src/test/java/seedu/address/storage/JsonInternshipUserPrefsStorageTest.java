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
import seedu.address.model.InternshipUserPrefs;

public class JsonInternshipUserPrefsStorageTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonInternshipUserPrefsStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readInternshipUserPrefs_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readInternshipUserPrefs(null));
    }

    private Optional<InternshipUserPrefs> readInternshipUserPrefs(String internshipUserPrefsFileInTestDataFolder)
            throws DataLoadingException {
        Path prefsFilePath = addToTestDataPathIfNotNull(internshipUserPrefsFileInTestDataFolder);
        return new JsonInternshipUserPrefsStorage(prefsFilePath).readUserPrefs(prefsFilePath);
    }

    @Test
    public void readInternshipUserPrefs_missingFile_emptyResult() throws DataLoadingException {
        assertFalse(readInternshipUserPrefs("NonExistentFile.json").isPresent());
    }

    @Test
    public void readInternshipUserPrefs_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () ->
                readInternshipUserPrefs("NotJsonFormatInternshipUserPrefs.json")
        );
    }

    private Path addToTestDataPathIfNotNull(String internshipUserPrefsFileInTestDataFolder) {
        return internshipUserPrefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(internshipUserPrefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void readInternshipUserPrefs_fileInOrder_successfullyRead() throws DataLoadingException {
        InternshipUserPrefs expected = getTypicalInternshipUserPrefs();
        InternshipUserPrefs actual = readInternshipUserPrefs(
                "TypicalInternshipUserPref.json").get();
        assertEquals(expected, actual);
    }

    @Test
    public void readInternshipUserPrefs_valuesMissingFromFile_defaultValuesUsed() throws DataLoadingException {
        InternshipUserPrefs actual = readInternshipUserPrefs(
                "EmptyInternshipUserPrefs.json").get();
        assertEquals(new InternshipUserPrefs(), actual);
    }

    @Test
    public void readInternshipUserPrefs_extraValuesInFile_extraValuesIgnored() throws DataLoadingException {
        InternshipUserPrefs expected = getTypicalInternshipUserPrefs();
        InternshipUserPrefs actual = readInternshipUserPrefs(
                "ExtraValuesInternshipUserPref.json").get();
        assertEquals(expected, actual);
    }

    private InternshipUserPrefs getTypicalInternshipUserPrefs() {
        InternshipUserPrefs internshipUserPrefs = new InternshipUserPrefs();
        internshipUserPrefs.setGuiSettings(
                new GuiSettings(1000, 500, 300, 100)
        );
        internshipUserPrefs.setInternshipFilePath(Paths.get("internshipBook.json"));
        return internshipUserPrefs;
    }

    @Test
    public void savePrefs_nullPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveInternshipUserPrefs(null,
                "SomeFile.json")
        );
    }

    @Test
    public void saveInternshipUserPrefs_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                saveInternshipUserPrefs(new InternshipUserPrefs(), null)
        );
    }

    /**
     * Saves {@code internshipUserPrefs} at the specified {@code prefsFileInTestDataFolder} filepath.
     */
    private void saveInternshipUserPrefs(InternshipUserPrefs internshipUserPrefs, String prefsFileInTestDataFolder) {
        try {
            new JsonInternshipUserPrefsStorage(addToTestDataPathIfNotNull(prefsFileInTestDataFolder))
                    .saveUserPrefs(internshipUserPrefs);
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file", ioe);
        }
    }

    @Test
    public void saveInternshipUserPrefs_allInOrder_success() throws DataLoadingException, IOException {

        InternshipUserPrefs original = new InternshipUserPrefs();
        original.setGuiSettings(new GuiSettings(1200, 200, 0, 2));

        Path pefsFilePath = testFolder.resolve("TempPrefs.json");
        JsonInternshipUserPrefsStorage jsonInternshipUserPrefsStorage =
                new JsonInternshipUserPrefsStorage(pefsFilePath);

        //Try writing when the file doesn't exist
        jsonInternshipUserPrefsStorage.saveUserPrefs(original);
        InternshipUserPrefs readBack = jsonInternshipUserPrefsStorage.readUserPrefs().get();
        assertEquals(original, readBack);

        //Try saving when the file exists
        original.setGuiSettings(new GuiSettings(5, 5, 5, 5));
        jsonInternshipUserPrefsStorage.saveUserPrefs(original);
        readBack = jsonInternshipUserPrefsStorage.readUserPrefs().get();
        assertEquals(original, readBack);
    }
}
