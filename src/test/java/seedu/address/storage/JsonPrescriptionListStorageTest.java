package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPrescriptions.CAFFOX;
import static seedu.address.testutil.TypicalPrescriptions.IBUPROFEN;
import static seedu.address.testutil.TypicalPrescriptions.NAPROXEN;
import static seedu.address.testutil.TypicalPrescriptions.getTypicalPrescriptionList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.PrescriptionList;
import seedu.address.model.ReadOnlyPrescriptionList;

public class JsonPrescriptionListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonPrescriptionListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readPrescriptionList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readPrescriptionList(null));
    }

    private java.util.Optional<ReadOnlyPrescriptionList> readPrescriptionList(String filePath) throws Exception {
        return new JsonPrescriptionListStorage(Paths.get(filePath))
            .readPrescriptionList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
            ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
            : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readPrescriptionList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readPrescriptionList("notJsonFormatPrescriptionList.json"));
    }

    @Test
    public void readPrescriptionList_invalidPrescriptionList_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readPrescriptionList("invalidPrescriptionList.json"));
    }

    @Test
    public void readPrescriptionList_invalidAndValidPrescriptionList_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readPrescriptionList("invalidAndValidPrescriptionList.json"));
    }

    @Test
    public void readAndSavePrescriptionList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempPrescriptionList.json");
        PrescriptionList original = getTypicalPrescriptionList();
        JsonPrescriptionListStorage jsonPrescriptionListStorage = new JsonPrescriptionListStorage(filePath);

        // Save in new file and read back
        jsonPrescriptionListStorage.savePrescriptionList(original, filePath);
        ReadOnlyPrescriptionList readBack = jsonPrescriptionListStorage.readPrescriptionList(filePath).get();
        assertEquals(original, new PrescriptionList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPrescription(IBUPROFEN);
        original.removePrescription(NAPROXEN);
        jsonPrescriptionListStorage.savePrescriptionList(original, filePath);
        readBack = jsonPrescriptionListStorage.readPrescriptionList(filePath).get();
        assertEquals(original, new PrescriptionList(readBack));

        // Save and read without specifying file path
        original.addPrescription(CAFFOX);
        jsonPrescriptionListStorage.savePrescriptionList(original); // file path not specified
        readBack = jsonPrescriptionListStorage.readPrescriptionList().get(); // file path not specified
        assertEquals(original, new PrescriptionList(readBack));

    }

    @Test
    public void savePrescriptionList_nullPrescriptionList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePrescriptionList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code prescriptionList} at the specified {@code filePath}.
     */
    private void savePrescriptionList(ReadOnlyPrescriptionList prescriptionList, String filePath) {
        try {
            new JsonPrescriptionListStorage(Paths.get(filePath))
                .savePrescriptionList(prescriptionList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void savePrescriptionList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePrescriptionList(new PrescriptionList(), null));
    }
}
