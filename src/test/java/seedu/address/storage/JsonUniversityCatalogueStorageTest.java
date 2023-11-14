package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalObjects.STANFORD;
import static seedu.address.testutil.TypicalObjects.WATERLOO;
import static seedu.address.testutil.TypicalObjects.YONSEI;
import static seedu.address.testutil.TypicalObjects.getTypicalUniversityCatalogue;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyUniversityCatalogue;
import seedu.address.model.UniversityCatalogue;

public class JsonUniversityCatalogueStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonUniversityCatalogueStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readUniversityCatalogue_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readUniversityCatalogue(null));
    }

    /**
     * filePath would be the relative path to file in the TEST_DATA_FOLDER
     */
    private Optional<ReadOnlyUniversityCatalogue> readUniversityCatalogue(String filePath) throws Exception {
        return new JsonUniversityCatalogueStorage(Paths.get(filePath))
                .readUniversityCatalogue(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readUniversityCatalogue("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () ->
                readUniversityCatalogue("notJsonFormatUniversityCatalogue.json"));
    }

    // DataLoadingException is thrown upon catching IllegalValueException, which can happen for
    // a number of reasons:
    // - Duplicate University
    // - Missing UniversityName
    // - Invalid UniversityName
    @Test
    public void readUniversityCatalogue_invalidUniversityCatalogue_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () ->
                readUniversityCatalogue("invalidUniversityCatalogue.json"));
    }

    @Test
    public void readUniversityCatalogue_invalidAndValidUniversityCatalogue_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () ->
                readUniversityCatalogue("invalidAndValidUniversityCatalogue.json"));
    }

    @Test
    public void readAndSaveUniversityCatalogue_allInOrder_success() throws Exception {
        // In TempDir
        Path filePath = testFolder.resolve("TempUniversityCatalogue.json");

        UniversityCatalogue original = getTypicalUniversityCatalogue();
        JsonUniversityCatalogueStorage jsonUniversityCatalogueStorage = new JsonUniversityCatalogueStorage(filePath);

        // Save in new file and read back
        jsonUniversityCatalogueStorage.saveUniversityCatalogue(original, filePath);
        ReadOnlyUniversityCatalogue readBack =
                jsonUniversityCatalogueStorage.readUniversityCatalogue(filePath).get();
        assertEquals(original, new UniversityCatalogue(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addUniversity(WATERLOO);
        original.removeUniversity(STANFORD);
        jsonUniversityCatalogueStorage.saveUniversityCatalogue(original, filePath);
        readBack = jsonUniversityCatalogueStorage.readUniversityCatalogue(filePath).get();
        assertEquals(original, new UniversityCatalogue(readBack));

        // Save and read without specifying file path
        original.addUniversity(YONSEI);
        jsonUniversityCatalogueStorage.saveUniversityCatalogue(original); // file path not specified
        readBack = jsonUniversityCatalogueStorage.readUniversityCatalogue().get(); // file path not specified
        assertEquals(original, new UniversityCatalogue(readBack));

    }

    @Test
    public void saveUniversityCatalogue_nullUniversityCatalogue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                saveUniversityCatalogue(null, "SomeFile.json"));
    }

    /**
     * Saves {@code universityCatalogue} at the specified {@code filePath}.
     */
    private void saveUniversityCatalogue(ReadOnlyUniversityCatalogue universityCatalogue, String filePath) {
        try {
            new JsonUniversityCatalogueStorage(Paths.get(filePath))
                    .saveUniversityCatalogue(universityCatalogue, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveUniversityCatalogue_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                saveUniversityCatalogue(new UniversityCatalogue(), null));
    }
}
