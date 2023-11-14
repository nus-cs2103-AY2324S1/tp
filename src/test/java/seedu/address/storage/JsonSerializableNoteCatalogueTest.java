package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.NoteCatalogue;
import seedu.address.testutil.TypicalObjects;

public class JsonSerializableNoteCatalogueTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableNoteCatalogueTest");

    private static final Path TYPICAL_NOTES_FILE = TEST_DATA_FOLDER.resolve("typicalNoteCatalogue.json");

    private static final Path INVALID_NOTES_EMPTY_CONTENT_FILE = TEST_DATA_FOLDER
            .resolve("invalidNoteEmptyContentInCatalogue.json");

    private static final Path INVALID_NOTES_INDEX_FILE = TEST_DATA_FOLDER
            .resolve("invalidNoteIndexInCatalogue.json");

    private static final Path INVALID_NOTES_DUPLICATE_INDEX_FILE = TEST_DATA_FOLDER
            .resolve("invalidNoteDuplicateIndexInCatalogue.json");

    @Test
    public void toModelType_typicalNotesFile_success() throws Exception {
        JsonSerializableNoteCatalogue dataFromFile = JsonUtil.readJsonFile(TYPICAL_NOTES_FILE,
                JsonSerializableNoteCatalogue.class).get();
        NoteCatalogue noteCatalogueFromFile = dataFromFile.toModelType();
        NoteCatalogue typicalCatalogue = TypicalObjects.getTypicalNoteCatalogue();
        assertEquals(noteCatalogueFromFile, typicalCatalogue);
    }

    @Test
    public void toModelTpe_invalidNotesEmptyContentInCatalogue_throwsIllegalValueException() throws Exception {
        JsonSerializableNoteCatalogue dataFromFile = JsonUtil.readJsonFile(INVALID_NOTES_EMPTY_CONTENT_FILE,
                JsonSerializableNoteCatalogue.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelTpe_invalidNotesIndexInCatalogue_throwsIllegalValueException() throws Exception {
        JsonSerializableNoteCatalogue dataFromFile = JsonUtil.readJsonFile(INVALID_NOTES_INDEX_FILE,
                JsonSerializableNoteCatalogue.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelTpe_invalidNotesDuplicateIndexInCatalogue_throwsIllegalValueException() throws Exception {
        JsonSerializableNoteCatalogue dataFromFile = JsonUtil.readJsonFile(INVALID_NOTES_DUPLICATE_INDEX_FILE,
                JsonSerializableNoteCatalogue.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }
}
