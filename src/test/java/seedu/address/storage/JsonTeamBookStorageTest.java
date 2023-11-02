package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTeams.getTypicalTeamBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyTeamBook;
import seedu.address.model.TeamBook;

public class JsonTeamBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTeamBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTeamBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTeamBook(null));
    }

    private java.util.Optional<ReadOnlyTeamBook> readTeamBook(String filePath) throws Exception {
        return new JsonTeamBookStorage(Paths.get(filePath)).readTeamBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTeamBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readTeamBook("notJsonFormatTeamBook.json"));
    }

    @Test
    public void readTeamBook_invalidTeamTeamBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readTeamBook("invalidTeamTeamBook.json"));
    }

    @Test
    public void readTeamBook_invalidAndValidTeamTeamBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readTeamBook("invalidAndValidTeamTeamBook.json"));
    }

    @Test
    public void readAndSaveTeamBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTeamBook.json");
        TeamBook original = getTypicalTeamBook();
        JsonTeamBookStorage jsonTeamBookStorage = new JsonTeamBookStorage(filePath);

        // Save in new file and read back
        jsonTeamBookStorage.saveTeamBook(original, filePath);
        ReadOnlyTeamBook readBack = jsonTeamBookStorage.readTeamBook(filePath).get();
        assertEquals(original, new TeamBook(readBack));

        // Modify data, overwrite existing file, and read back
        // Assuming you have methods to modify teams
        // original.addTeam(newTeam);
        // original.removeTeam(someTeam);
        jsonTeamBookStorage.saveTeamBook(original, filePath);
        readBack = jsonTeamBookStorage.readTeamBook(filePath).get();
        assertEquals(original, new TeamBook(readBack));

        // Save and read without specifying file path
        // original.addTeam(anotherNewTeam);
        jsonTeamBookStorage.saveTeamBook(original); // file path not specified
        readBack = jsonTeamBookStorage.readTeamBook().get(); // file path not specified
        assertEquals(original, new TeamBook(readBack));

    }

    @Test
    public void saveTeamBook_nullTeamBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTeamBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code teamBook} at the specified {@code filePath}.
     */
    private void saveTeamBook(ReadOnlyTeamBook teamBook, String filePath) {
        try {
            new JsonTeamBookStorage(Paths.get(filePath))
                    .saveTeamBook(teamBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTeamBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTeamBook(new TeamBook(), null));
    }
}
