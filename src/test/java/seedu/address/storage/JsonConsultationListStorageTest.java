package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalConsultations.CONSULTATION3;
import static seedu.address.testutil.TypicalConsultations.CONSULTATION4;
import static seedu.address.testutil.TypicalConsultations.getTypicalConsultationListBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ConsultationListBook;
import seedu.address.model.ReadOnlyConsultationList;

public class JsonConsultationListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonConsultationListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readConsultation_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readConsultationListBook(null));
    }

    private java.util.Optional<ReadOnlyConsultationList> readConsultationListBook(String filePath) throws Exception {
        return new JsonConsultationListStorage(Paths.get(filePath))
                .readConsultationList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readConsultationListBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () ->
                readConsultationListBook("notJsonFormatConsultationListBook.json"));
    }

    @Test
    public void readConsultationListBook_invalidDate_throwDateTimeParseException() {
        assertThrows(DateTimeParseException.class, () ->
                readConsultationListBook("invalidDateConsultationListBook.json"));
    }

    @Test
    public void readConsultationListBook_invalidTime_throwDateTimeParseException() {
        assertThrows(DateTimeParseException.class, () ->
                readConsultationListBook("invalidTimeConsultationListBook.json"));
    }

    @Test
    public void readAndSaveConsultationListBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempConsultationListBook.json");
        ConsultationListBook original = getTypicalConsultationListBook();
        JsonConsultationListStorage jsonConsultationListStorage = new JsonConsultationListStorage(filePath);

        // Save in new file and read back
        jsonConsultationListStorage.saveConsultationList(original, filePath);
        ReadOnlyConsultationList readBack = jsonConsultationListStorage.readConsultationList(filePath).get();
        assertEquals(original, new ConsultationListBook(readBack));

        // Modify data, overwrite existing file, and read back
        original.addConsultation(CONSULTATION4);
        jsonConsultationListStorage.saveConsultationList(original, filePath);
        readBack = jsonConsultationListStorage.readConsultationList(filePath).get();
        assertEquals(original, new ConsultationListBook(readBack));

        // Save and read without specifying file path
        original.addConsultation(CONSULTATION3);
        jsonConsultationListStorage.saveConsultationList(original);
        readBack = jsonConsultationListStorage.readConsultationList().get();
        assertEquals(original, new ConsultationListBook(readBack));
    }

    @Test
    public void saveConsultationListBook_nullConsultationListBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                saveConsultationListBook(null, "SomeFile.json"));
    }

    @Test
    public void saveConsultationListBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                saveConsultationListBook(new ConsultationListBook(), null));
    }

    /**
     * Saves {@code consultationListBook} at the specified {@code filePath}.
     */
    private void saveConsultationListBook(ReadOnlyConsultationList consultationListBook, String filePath) {
        try {
            new JsonConsultationListStorage(Paths.get(filePath))
                    .saveConsultationList(consultationListBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

}
