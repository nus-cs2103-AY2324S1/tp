package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalObjects.getTypicalUniversityCatalogue;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.UniversityCatalogue;

public class JsonSerializableUniversityCatalogueTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableUniversityCatalogueTest");
    private static final Path TYPICAL_UNIVERSITIES_FILE =
            TEST_DATA_FOLDER.resolve("typicalUniversityCatalogue.json");
    private static final Path INVALID_UNIVERSITIES_FILE = TEST_DATA_FOLDER.resolve(
            "invalidUniversityInCatalogue.json");
    private static final Path DUPLICATE_UNIVERSITIES_FILE = TEST_DATA_FOLDER.resolve(
            "duplicateUniversityInCatalogue.json");

    @Test
    public void toModelType_typicalUniversityFile_success() throws Exception {
        JsonSerializableUniversityCatalogue dataFromFile = JsonUtil.readJsonFile(TYPICAL_UNIVERSITIES_FILE,
                JsonSerializableUniversityCatalogue.class).get();
        UniversityCatalogue universityCatalogueFromFile = dataFromFile.toModelType();
        UniversityCatalogue typicalUniversityCatalogue = getTypicalUniversityCatalogue();
        assertEquals(universityCatalogueFromFile, typicalUniversityCatalogue);
    }

    @Test
    public void toModelType_invalidUniversitiesCatalogueFile_throwsIllegalValueException() throws Exception {
        JsonSerializableUniversityCatalogue dataFromFile = JsonUtil.readJsonFile(INVALID_UNIVERSITIES_FILE,
                JsonSerializableUniversityCatalogue.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateUniversities_throwsIllegalValueException() throws Exception {
        JsonSerializableUniversityCatalogue dataFromFile = JsonUtil.readJsonFile(DUPLICATE_UNIVERSITIES_FILE,
                JsonSerializableUniversityCatalogue.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableUniversityCatalogue.MESSAGE_DUPLICATE_UNIVERSITY,
                dataFromFile::toModelType);
    }
}
