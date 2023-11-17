package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AnimalCatalog;
import seedu.address.testutil.TypicalAnimals;

public class JsonSerializableAnimalCatalogTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAnimalCatalogTest");
    private static final Path TYPICAL_ANIMALS_FILE = TEST_DATA_FOLDER.resolve("typicalAnimalsCatalog.json");
    private static final Path INVALID_ANIMAL_FILE = TEST_DATA_FOLDER.resolve("invalidAnimalsInCatalog.json");
    private static final Path DUPLICATE_ANIMAL_FILE = TEST_DATA_FOLDER.resolve("duplicateAnimalsInCatalog.json");

    @Test
    public void toAnimalCatalogModel_typicalAnimalsFile_success() throws Exception {
        JsonSerializableAnimalCatalog dataFromFile =
            JsonUtil.readJsonFile(TYPICAL_ANIMALS_FILE, JsonSerializableAnimalCatalog.class).get();

        AnimalCatalog animalCatalogFromFile = dataFromFile.toAnimalCatalogModel();
        AnimalCatalog typicalAnimalsCatalog = TypicalAnimals.getTypicalCatalog();
        assertEquals(animalCatalogFromFile, typicalAnimalsCatalog);
    }

    @Test
    public void toAnimalCatalogModelType_invalidAnimalsFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAnimalCatalog dataFromFile =
            JsonUtil.readJsonFile(INVALID_ANIMAL_FILE, JsonSerializableAnimalCatalog.class).get();

        assertThrows(IllegalValueException.class, dataFromFile::toAnimalCatalogModel);
    }

    @Test
    public void toAnimalCatalogModel_duplicateAnimals_throwsIllegalValueException() throws Exception {
        JsonSerializableAnimalCatalog dataFromFile =
            JsonUtil.readJsonFile(DUPLICATE_ANIMAL_FILE, JsonSerializableAnimalCatalog.class).get();

        assertThrows(IllegalValueException.class,
            JsonSerializableAnimalCatalog.MESSAGE_DUPLICATE_ANIMAL, dataFromFile::toAnimalCatalogModel);
    }

}
