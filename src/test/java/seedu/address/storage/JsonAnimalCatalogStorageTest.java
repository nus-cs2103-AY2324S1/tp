package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAnimals.JAEBEE;
import static seedu.address.testutil.TypicalAnimals.LOYBOY;
import static seedu.address.testutil.TypicalAnimals.MUFFIN;
import static seedu.address.testutil.TypicalAnimals.getTypicalCatalog;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.AnimalCatalog;
import seedu.address.model.ReadOnlyAnimalCatalog;

public class JsonAnimalCatalogStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAnimalCatalogStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAnimalCatalog_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAnimalCatalog(null));
    }

    private Optional<ReadOnlyAnimalCatalog> readAnimalCatalog(String filePath) throws Exception {
        return new JsonAnimalCatalogStorage(Paths.get(filePath))
            .readAnimalCatalog(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void readAnimalCatalog_missingFile_emptyResult() throws Exception {
        assertFalse(readAnimalCatalog("NonExistentFile.json").isPresent());
    }

    @Test
    public void readAnimalCatalog_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readAnimalCatalog("notJsonFormatCatalog.json"));
    }

    @Test
    public void readAnimalCatalog_invalidAnimalsInCatalog_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readAnimalCatalog("invalidAnimalsInCatalog.json"));
    }

    @Test
    public void readAnimalCatalog_invalidAndValidAnimalsInCatalog_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readAnimalCatalog("invalidAndValidAnimalsInCatalog.json"));
    }

    @Test
    public void readAndSaveAnimalCatalog_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAnimalCatalog.json");
        AnimalCatalog original = getTypicalCatalog();
        JsonAnimalCatalogStorage jsonAnimalCatalogStorage = new JsonAnimalCatalogStorage(filePath);

        // Save in new file and read back
        jsonAnimalCatalogStorage.saveAnimalCatalog(original, filePath);
        ReadOnlyAnimalCatalog readBack = jsonAnimalCatalogStorage.readAnimalCatalog(filePath).get();
        assertEquals(original, new AnimalCatalog(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addAnimal(JAEBEE);
        original.removeAnimal(MUFFIN);
        jsonAnimalCatalogStorage.saveAnimalCatalog(original, filePath);
        readBack = jsonAnimalCatalogStorage.readAnimalCatalog(filePath).get();
        assertEquals(original, new AnimalCatalog(readBack));

        // Save and read without specifying file path
        original.addAnimal(LOYBOY);
        jsonAnimalCatalogStorage.saveAnimalCatalog(original); // file path not specified
        readBack = jsonAnimalCatalogStorage.readAnimalCatalog().get(); // file path not specified
        assertEquals(original, new AnimalCatalog(readBack));

    }

    @Test
    public void saveAnimalCatalog_nullAnimalCatalog_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAnimalCatalog(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAnimalCatalog(AnimalCatalog animalCatalog, String filePath) {
        try {
            new JsonAnimalCatalogStorage(Paths.get(filePath))
                    .saveAnimalCatalog(animalCatalog, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAnimalCatalog_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAnimalCatalog(new AnimalCatalog(), null));
    }

    @Test
    public void getAnimalCatalogFilePathTest() {
        JsonAnimalCatalogStorage testStorage = new JsonAnimalCatalogStorage(TEST_DATA_FOLDER);
        assertEquals(testStorage.getAnimalCatalogFilePath(), TEST_DATA_FOLDER);
    }
}
