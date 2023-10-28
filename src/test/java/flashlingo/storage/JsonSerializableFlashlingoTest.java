package flashlingo.storage;

import flashlingo.testutil.TypicalFlashCards;
import seedu.flashlingo.storage.JsonSerializableFlashlingo;
import org.junit.jupiter.api.Test;
import seedu.flashlingo.commons.exceptions.IllegalValueException;
import seedu.flashlingo.commons.util.JsonUtil;
import seedu.flashlingo.model.Flashlingo;


import java.nio.file.Path;
import java.nio.file.Paths;

import static flashlingo.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonSerializableFlashlingoTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableFlashlingoTest");
    private static final Path TYPICAL_FLASHCARDS_FILE = TEST_DATA_FOLDER.resolve("typicalFlashCardsFlashlingo.json");
    private static final Path INVALID_FLASHCARD_FILE = TEST_DATA_FOLDER.resolve("invalidFlashCardFlashlingo.json");
    private static final Path DUPLICATE_FLASHCARD_FILE = TEST_DATA_FOLDER.resolve("duplicateFlashCardFlashlingo.json");

//    @Test
//    public void toModelType_typicalFlashCardsFile_success() throws Exception {
//        JsonSerializableFlashlingo dataFromFile = JsonUtil.readJsonFile(TYPICAL_FLASHCARDS_FILE,
//                JsonSerializableFlashlingo.class).get();
//        Flashlingo flashlingoFromFile = dataFromFile.toModelType();
//        Flashlingo typicalFlashCardsFlashlingo = TypicalFlashCards.getTypicalFlashlingo();
//        assertEquals(flashlingoFromFile, typicalFlashCardsFlashlingo);
//    }

    @Test
    public void toModelType_invalidFlashCardsFile_throwsIllegalValueException() throws Exception {
        JsonSerializableFlashlingo dataFromFile = JsonUtil.readJsonFile(INVALID_FLASHCARD_FILE,
                JsonSerializableFlashlingo.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateFlashCards_throwsIllegalValueException() throws Exception {
        JsonSerializableFlashlingo dataFromFile = JsonUtil.readJsonFile(DUPLICATE_FLASHCARD_FILE,
                JsonSerializableFlashlingo.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableFlashlingo.MESSAGE_DUPLICATE_FLASHCARD,
                dataFromFile::toModelType);
    }
}
