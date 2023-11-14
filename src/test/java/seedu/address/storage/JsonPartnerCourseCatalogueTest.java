package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalObjects.COMP2000;
import static seedu.address.testutil.TypicalObjects.COMP3000;
import static seedu.address.testutil.TypicalObjects.COMP4000;
import static seedu.address.testutil.TypicalObjects.getTypicalPartnerCourseCatalogue;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.PartnerCourseCatalogue;
import seedu.address.model.ReadOnlyPartnerCourseCatalogue;

public class JsonPartnerCourseCatalogueTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonPartnerCourseCatalogueStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readPartnerCourseCatalogue_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readPartnerCourseCatalogue(null));
    }

    /**
     * filePath would be the relative path to file in the TEST_DATA_FOLDER
     */
    private Optional<ReadOnlyPartnerCourseCatalogue> readPartnerCourseCatalogue(String filePath) throws Exception {
        return new JsonPartnerCourseCatalogueStorage(Paths.get(filePath))
                .readPartnerCourseCatalogue(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readPartnerCourseCatalogue("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () ->
                readPartnerCourseCatalogue("notJsonFormatPartnerCourseCatalogue.json"));
    }

    // DataLoadingException is thrown upon catching IllegalValueException, which can happen for
    // a number of reasons:
    // - Duplicate PartnerCourse
    // - Missing PartnerCode / PartnerName
    // - Invalid PartnerCode / PartnerName
    @Test
    public void readPartnerCourseCatalogue_invalidPartnerCourseCatalogue_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () ->
                readPartnerCourseCatalogue("invalidPartnerCourseCatalogue.json"));
    }

    @Test
    public void readPartnerCourseCatalogue_invalidAndValidPartnerCourseCatalogue_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () ->
                readPartnerCourseCatalogue("invalidAndValidPartnerCourseCatalogue.json"));
    }

    @Test
    public void readAndSavePartnerCourseCatalogue_allInOrder_success() throws Exception {
        // In TempDir
        Path filePath = testFolder.resolve("TempPartnerCourseCatalogue.json");

        PartnerCourseCatalogue original = getTypicalPartnerCourseCatalogue();
        JsonPartnerCourseCatalogueStorage jsonPartnerCourseCatalogueStorage =
                new JsonPartnerCourseCatalogueStorage(filePath);

        // Save in new file and read back
        jsonPartnerCourseCatalogueStorage.savePartnerCourseCatalogue(original, filePath);
        ReadOnlyPartnerCourseCatalogue readBack =
                jsonPartnerCourseCatalogueStorage.readPartnerCourseCatalogue(filePath).get();
        assertEquals(original, new PartnerCourseCatalogue(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPartnerCourse(COMP3000);
        original.removePartnerCourse(COMP2000);
        jsonPartnerCourseCatalogueStorage.savePartnerCourseCatalogue(original, filePath);
        readBack = jsonPartnerCourseCatalogueStorage.readPartnerCourseCatalogue(filePath).get();
        assertEquals(original, new PartnerCourseCatalogue(readBack));

        // Save and read without specifying file path
        original.addPartnerCourse(COMP4000);
        jsonPartnerCourseCatalogueStorage.savePartnerCourseCatalogue(original); // file path not specified
        readBack = jsonPartnerCourseCatalogueStorage.readPartnerCourseCatalogue().get(); // file path not specified
        assertEquals(original, new PartnerCourseCatalogue(readBack));

    }

    @Test
    public void savePartnerCourseCatalogue_nullPartnerCourseCatalogue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                savePartnerCourseCatalogue(null, "SomeFile.json"));
    }

    /**
     * Saves {@code partnerCourseCatalogue} at the specified {@code filePath}.
     */
    private void savePartnerCourseCatalogue(ReadOnlyPartnerCourseCatalogue partnerCourseCatalogue, String filePath) {
        try {
            new JsonPartnerCourseCatalogueStorage(Paths.get(filePath))
                    .savePartnerCourseCatalogue(partnerCourseCatalogue, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void savePartnerCourseCatalogue_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                savePartnerCourseCatalogue(new PartnerCourseCatalogue(), null));
    }
}
