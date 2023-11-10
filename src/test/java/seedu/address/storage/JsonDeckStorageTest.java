package seedu.address.storage;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCards.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.Deck;
import seedu.address.model.ReadOnlyDeck;

public class JsonDeckStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonDeckStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readDeck_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readDeck(null));
    }

    private java.util.Optional<ReadOnlyDeck> readDeck(String filePath) throws Exception {
        return new JsonDeckStorage(Paths.get(filePath)).readDeck(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() {
        Path path = Paths.get("NonExistentFile.json");

        try {
            Optional<ReadOnlyDeck> result = readDeck("NonExistentFile.json");
            assertTrue(result.isPresent());
        } catch (Exception e) {
            fail("Exception should not be thrown");
        } finally {
            try {
                Path nonExistentFilePath = TEST_DATA_FOLDER.resolve("NonExistentFile.json");
                boolean deleted = Files.deleteIfExists(nonExistentFilePath);
                if (!deleted) {
                    fail("Test file was not deleted because it did not exist." + path);
                }
            } catch (IOException e) {
                e.printStackTrace();
                fail("Could not clean up the test file due to an IOException: " + e.getMessage());
            }
        }
    }



    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readDeck("notJsonFormatDeck.json"));
    }

    @Test
    public void readDeck_invalidCardDeckMissingAnswer_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readDeck("invalidCardDeckMissingAnswer.json"));
    }

    @Test
    public void readDeck_invalidCardDeckMissingNextPracticeDate_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readDeck("invalidCardDeckMissingNextPracticeDate.json"));
    }

    @Test
    public void readDeck_invalidCardDeckInvalidNextPracticeDate_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readDeck("invalidCardDeckInvalidNextPracticeDate.json"));
    }

    @Test
    public void readDeck_invalidAndValidCardDeck_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readDeck("invalidAndValidCardDeck.json"));
    }

    @Test
    public void readAndSaveDeck_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempDeck.json");
        Deck original = getTypicalDeck();
        JsonDeckStorage jsonDeckStorage = new JsonDeckStorage(filePath);

        // Save in new file and read back
        jsonDeckStorage.saveDeck(original, filePath);
        ReadOnlyDeck readBack = jsonDeckStorage.readDeck(filePath).get();
        assertEquals(original, new Deck(readBack));

        // Modify data, overwrite exiting file, and read back
        original.removeCard(CS1101S);
        jsonDeckStorage.saveDeck(original, filePath);
        readBack = jsonDeckStorage.readDeck(filePath).get();
        assertEquals(original, new Deck(readBack));

        // Save and read without specifying file path
        jsonDeckStorage.saveDeck(original); // file path not specified
        readBack = jsonDeckStorage.readDeck().get(); // file path not specified
        assertEquals(original, new Deck(readBack));

    }

    @Test
    public void saveDeck_nullDeck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveDeck(null, "SomeFile.json"));
    }

    /**
     * Saves {@code Deck} at the specified {@code filePath}.
     */
    private void saveDeck(ReadOnlyDeck deck, String filePath) {
        try {
            new JsonDeckStorage(Paths.get(filePath))
                    .saveDeck(deck, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveDeck_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveDeck(new Deck(), null));
    }
}
