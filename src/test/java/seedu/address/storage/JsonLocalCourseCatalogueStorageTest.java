package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalObjects.CS2030S;
import static seedu.address.testutil.TypicalObjects.CS3230;
import static seedu.address.testutil.TypicalObjects.MA1521;
import static seedu.address.testutil.TypicalObjects.getTypicalLocalCourseCatalogue;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.LocalCourseCatalogue;
import seedu.address.model.ReadOnlyLocalCourseCatalogue;

public class JsonLocalCourseCatalogueStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonLocalCourseCatalogueStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readLocalCourseCatalogue_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readLocalCourseCatalogue(null));
    }

    /**
     * filePath would be the relative path to file in the TEST_DATA_FOLDER
     */
    private Optional<ReadOnlyLocalCourseCatalogue> readLocalCourseCatalogue(String filePath) throws Exception {
        return new JsonLocalCourseCatalogueStorage(Paths.get(filePath))
                .readLocalCourseCatalogue(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readLocalCourseCatalogue("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () ->
                readLocalCourseCatalogue("notJsonFormatLocalCourseCatalogue.json"));
    }

    // DataLoadingException is thrown upon catching IllegalValueException, which can happen for
    // a number of reasons:
    // - Duplicate LocalCourse
    // - Missing LocalCode / LocalName
    // - Invalid LocalCode / LocalName
    @Test
    public void readLocalCourseCatalogue_invalidLocalCourseCatalogue_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () ->
                readLocalCourseCatalogue("invalidLocalCourseCatalogue.json"));
    }

    @Test
    public void readLocalCourseCatalogue_invalidAndValidLocalCourseCatalogue_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () ->
                readLocalCourseCatalogue("invalidAndValidLocalCourseCatalogue.json"));
    }

    @Test
    public void readAndSaveLocalCourseCatalogue_allInOrder_success() throws Exception {
        // In TempDir
        Path filePath = testFolder.resolve("TempLocalCourseCatalogue.json");

        LocalCourseCatalogue original = getTypicalLocalCourseCatalogue();
        JsonLocalCourseCatalogueStorage jsonLocalCourseCatalogueStorage = new JsonLocalCourseCatalogueStorage(filePath);

        // Save in new file and read back
        jsonLocalCourseCatalogueStorage.saveLocalCourseCatalogue(original, filePath);
        ReadOnlyLocalCourseCatalogue readBack =
                jsonLocalCourseCatalogueStorage.readLocalCourseCatalogue(filePath).get();
        assertEquals(original, new LocalCourseCatalogue(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addLocalCourse(MA1521);
        original.removeLocalCourse(CS3230);
        jsonLocalCourseCatalogueStorage.saveLocalCourseCatalogue(original, filePath);
        readBack = jsonLocalCourseCatalogueStorage.readLocalCourseCatalogue(filePath).get();
        assertEquals(original, new LocalCourseCatalogue(readBack));

        // Save and read without specifying file path
        original.addLocalCourse(CS2030S);
        jsonLocalCourseCatalogueStorage.saveLocalCourseCatalogue(original); // file path not specified
        readBack = jsonLocalCourseCatalogueStorage.readLocalCourseCatalogue().get(); // file path not specified
        assertEquals(original, new LocalCourseCatalogue(readBack));

    }

    @Test
    public void saveLocalCourseCatalogue_nullLocalCourseCatalogue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                saveLocalCourseCatalogue(null, "SomeFile.json"));
    }

    /**
     * Saves {@code localCourseCatalogue} at the specified {@code filePath}.
     */
    private void saveLocalCourseCatalogue(ReadOnlyLocalCourseCatalogue localCourseCatalogue, String filePath) {
        try {
            new JsonLocalCourseCatalogueStorage(Paths.get(filePath))
                    .saveLocalCourseCatalogue(localCourseCatalogue, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveLocalCourseCatalogue_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                saveLocalCourseCatalogue(new LocalCourseCatalogue(), null));
    }
}
