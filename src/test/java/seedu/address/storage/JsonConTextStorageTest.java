package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TestData.ALICE;
import static seedu.address.testutil.TestData.HOON;
import static seedu.address.testutil.TestData.IDA;
import static seedu.address.testutil.TestData.getTypicalConText;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ConText;
import seedu.address.model.ReadOnlyConText;

public class JsonConTextStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonConTextStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readConText_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readConText(null));
    }

    private java.util.Optional<ReadOnlyConText> readConText(String filePath) throws Exception {
        return new JsonConTextStorage(Paths.get(filePath)).readConText(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readConText("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readConText("notJsonFormatConText.json"));
    }

    @Test
    public void readConText_invalidContactConText_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readConText("invalidContactConText.json"));
    }

    @Test
    public void readConText_invalidAndValidContactConText_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readConText("invalidAndValidContactConText.json"));
    }

    @Test
    public void readAndSaveConText_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempConText.json");
        ConText original = getTypicalConText();
        JsonConTextStorage jsonConTextStorage = new JsonConTextStorage(filePath);

        // Save in new file and read back
        jsonConTextStorage.saveConText(original, filePath);
        ReadOnlyConText readBack = jsonConTextStorage.readConText(filePath).get();
        assertEquals(original, new ConText(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addContact(HOON);
        original.removeContact(ALICE);
        jsonConTextStorage.saveConText(original, filePath);
        readBack = jsonConTextStorage.readConText(filePath).get();
        assertEquals(original, new ConText(readBack));

        // Save and read without specifying file path
        original.addContact(IDA);
        jsonConTextStorage.saveConText(original); // file path not specified
        readBack = jsonConTextStorage.readConText().get(); // file path not specified
        assertEquals(original, new ConText(readBack));

    }

    @Test
    public void saveConText_nullConText_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveConText(null, "SomeFile.json"));
    }

    /**
     * Saves {@code ConText} at the specified {@code filePath}.
     */
    private void saveConText(ReadOnlyConText conText, String filePath) {
        try {
            new JsonConTextStorage(Paths.get(filePath))
                    .saveConText(conText, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveConText_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveConText(new ConText(), null));
    }
}
