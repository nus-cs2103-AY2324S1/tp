package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ELLE;
import static seedu.address.testutil.TypicalStudents.HOON;
import static seedu.address.testutil.TypicalStudents.IDA;
import static seedu.address.testutil.TypicalWellNus.getTypicalWellNus;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyWellNus;
import seedu.address.model.WellNus;

public class JsonWellNusStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonWellNusStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readWellNus_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readWellNus(null));
    }

    private java.util.Optional<ReadOnlyWellNus> readWellNus(String filePath) throws Exception {
        return new JsonWellNusStorage(Paths.get(filePath)).readWellNus(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readWellNus("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readWellNus("notJsonFormatWellNus.json"));
    }

    @Test
    public void readWellNus_invalidStudentWellNus_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readWellNus("invalidStudentWellNus.json"));
    }

    @Test
    public void readWellNus_invalidAndValidStudentWellNus_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readWellNus("invalidAndValidStudentWellNus.json"));
    }

    @Test
    public void readWellNus_invalidAppointmentWellNus_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readWellNus("invalidAppointmentWellNus.json"));
    }

    @Test
    public void readWellNus_invalidAndValidAppointmentWellNus_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readWellNus("invalidAndValidAppointmentWellNus.json"));
    }

    @Test
    public void readAndSaveWellNus_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempWellNus.json");
        WellNus original = getTypicalWellNus();
        JsonWellNusStorage jsonWellNusStorage = new JsonWellNusStorage(filePath);

        // Save in new file and read back
        jsonWellNusStorage.saveWellNus(original, filePath);
        ReadOnlyWellNus readBack = jsonWellNusStorage.readWellNus(filePath).get();
        assertEquals(original, new WellNus(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStudent(HOON);
        original.removeStudent(ELLE);
        jsonWellNusStorage.saveWellNus(original, filePath);
        readBack = jsonWellNusStorage.readWellNus(filePath).get();
        assertEquals(original, new WellNus(readBack));

        // Save and read without specifying file path
        original.addStudent(IDA);
        jsonWellNusStorage.saveWellNus(original); // file path not specified
        readBack = jsonWellNusStorage.readWellNus().get(); // file path not specified
        assertEquals(original, new WellNus(readBack));

    }

    @Test
    public void saveWellNus_nullWellNus_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveWellNus(null, "SomeFile.json"));
    }

    /**
     * Saves {@code wellNus} at the specified {@code filePath}.
     */
    private void saveWellNus(ReadOnlyWellNus wellNus, String filePath) {
        try {
            new JsonWellNusStorage(Paths.get(filePath))
                    .saveWellNus(wellNus, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveWellNus_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveWellNus(new WellNus(), null));
    }
}
