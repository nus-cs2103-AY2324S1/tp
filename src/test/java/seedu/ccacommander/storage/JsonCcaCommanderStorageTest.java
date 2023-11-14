package seedu.ccacommander.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.ccacommander.testutil.Assert.assertThrows;
import static seedu.ccacommander.testutil.TypicalCcaCommander.getTypicalCcaCommander;
import static seedu.ccacommander.testutil.TypicalMembers.ALICE;
import static seedu.ccacommander.testutil.TypicalMembers.HOON;
import static seedu.ccacommander.testutil.TypicalMembers.IDA;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.ccacommander.commons.exceptions.DataLoadingException;
import seedu.ccacommander.model.CcaCommander;
import seedu.ccacommander.model.ReadOnlyCcaCommander;

public class JsonCcaCommanderStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonCcaCommanderStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readCcaCommander_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readCcaCommander(null));
    }

    private java.util.Optional<ReadOnlyCcaCommander> readCcaCommander(String filePath) throws Exception {
        return new JsonCcaCommanderStorage(Paths.get(filePath)).readCcaCommander(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readCcaCommander("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readCcaCommander("notJsonFormatCcaCommander.json"));
    }

    @Test
    public void readCcaCommander_invalidMemberCcaCommander_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readCcaCommander("invalidMemberCcaCommander.json"));
    }

    @Test
    public void readCcaCommander_invalidAndValidMemberCcaCommander_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readCcaCommander("invalidAndValidMemberCcaCommander.json"));
    }

    @Test
    public void readAndSaveCcaCommander_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempCcaCommander.json");
        CcaCommander original = getTypicalCcaCommander();
        JsonCcaCommanderStorage jsonCcaCommanderStorage = new JsonCcaCommanderStorage(filePath);

        // Ensure there is a file path
        assertNotNull(jsonCcaCommanderStorage.getCcaCommanderFilePath());

        // Save in new file and read back
        jsonCcaCommanderStorage.saveCcaCommander(original, filePath);
        ReadOnlyCcaCommander readBack = jsonCcaCommanderStorage.readCcaCommander(filePath).get();
        assertEquals(original, new CcaCommander(readBack));

        // Modify data, overwrite exiting file, and read back
        original.createMember(HOON);
        original.removeMember(ALICE);
        jsonCcaCommanderStorage.saveCcaCommander(original, filePath);
        readBack = jsonCcaCommanderStorage.readCcaCommander(filePath).get();
        assertEquals(original, new CcaCommander(readBack));

        // Save and read without specifying file path
        original.createMember(IDA);
        jsonCcaCommanderStorage.saveCcaCommander(original); // file path not specified
        readBack = jsonCcaCommanderStorage.readCcaCommander().get(); // file path not specified
        assertEquals(original, new CcaCommander(readBack));

    }

    @Test
    public void saveCcaCommander_nullCcaCommander_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCcaCommander(null, "SomeFile.json"));
    }

    /**
     * Saves {@code ccaCommander} at the specified {@code filePath}.
     */
    private void saveCcaCommander(ReadOnlyCcaCommander ccaCommander, String filePath) {
        try {
            new JsonCcaCommanderStorage(Paths.get(filePath))
                    .saveCcaCommander(ccaCommander, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveCcaCommander_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCcaCommander(new CcaCommander(), null));
    }
}
