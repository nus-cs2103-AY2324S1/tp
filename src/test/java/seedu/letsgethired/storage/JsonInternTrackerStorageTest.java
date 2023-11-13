package seedu.letsgethired.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.letsgethired.testutil.Assert.assertThrows;
import static seedu.letsgethired.testutil.TypicalInternApplications.BOSCH;
import static seedu.letsgethired.testutil.TypicalInternApplications.JANE_STREET;
import static seedu.letsgethired.testutil.TypicalInternApplications.SCHNEIDER;
import static seedu.letsgethired.testutil.TypicalInternApplications.getTypicalInternTracker;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.letsgethired.commons.exceptions.DataLoadingException;
import seedu.letsgethired.model.InternTracker;
import seedu.letsgethired.model.ReadOnlyInternTracker;

public class JsonInternTrackerStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonInternTrackerStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readInternTracker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readInternTracker(null));
    }

    private java.util.Optional<ReadOnlyInternTracker> readInternTracker(String filePath) throws Exception {
        return new JsonInternTrackerStorage(Paths.get(filePath))
                .readInternTracker(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readInternTracker("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readInternTracker("notJsonFormatInternTracker.json"));
    }

    @Test
    public void readInternTracker_invalidInternApplicationInternTracker_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readInternTracker("invalidInternApplicationInternTracker.json"));
    }

    @Test
    public void readInternTracker_invalidAndValidInternApplicationInternTracker_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () ->
                readInternTracker("invalidAndValidInternApplicationInternTracker.json"));
    }

    @Test
    public void readAndSaveInternTracker_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempInternTracker.json");
        InternTracker original = getTypicalInternTracker();
        JsonInternTrackerStorage jsonInternTrackerStorage = new JsonInternTrackerStorage(filePath);

        // Save in new file and read back
        jsonInternTrackerStorage.saveInternTracker(original, filePath);
        ReadOnlyInternTracker readBack = jsonInternTrackerStorage.readInternTracker(filePath).get();
        assertEquals(original, new InternTracker(readBack));

        // Modify data, overwrite exiting file, and read back

        original.addApplication(BOSCH);
        original.removeApplication(JANE_STREET);
        jsonInternTrackerStorage.saveInternTracker(original, filePath);
        readBack = jsonInternTrackerStorage.readInternTracker(filePath).get();
        assertEquals(original, new InternTracker(readBack));

        // Save and read without specifying file path

        original.addApplication(SCHNEIDER);
        jsonInternTrackerStorage.saveInternTracker(original); // file path not specified
        readBack = jsonInternTrackerStorage.readInternTracker().get(); // file path not specified
        assertEquals(original, new InternTracker(readBack));

    }

    @Test
    public void saveInternTracker_nullInternTracker_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveInternTracker(null, "SomeFile.json"));
    }

    /**
     * Saves {@code internBook} at the specified {@code filePath}.
     */
    private void saveInternTracker(ReadOnlyInternTracker internBook, String filePath) {
        try {
            new JsonInternTrackerStorage(Paths.get(filePath))
                    .saveInternTracker(internBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveInternTracker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveInternTracker(new InternTracker(), null));
    }
}
