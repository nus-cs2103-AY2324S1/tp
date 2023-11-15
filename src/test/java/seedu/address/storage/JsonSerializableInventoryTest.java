package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Inventory;
import seedu.address.model.ingredient.exceptions.UnitFormatException;
import seedu.address.testutil.TypicalIngredients;

public class JsonSerializableInventoryTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableInventoryTest");
    private static final Path TYPICAL_INVENTORY_FILE = TEST_DATA_FOLDER.resolve("typicalIngredientInventory.json");
    private static final Path INVALID_INVENTORY_FILE = TEST_DATA_FOLDER.resolve("invalidIngredientInventory.json");
    private static final Path DUPLICATE_INGREDIENT_FILE =
        TEST_DATA_FOLDER.resolve("duplicateIngredientInventory.json");

    @Test
    public void toModelType_typicalIngredientsFile_success() throws Exception {
        JsonSerializableInventory dataFromFile = JsonUtil.readJsonFile(TYPICAL_INVENTORY_FILE,
                JsonSerializableInventory.class).get();
        Inventory inventoryFromFile = dataFromFile.toModelType();
        Inventory typicalIngredientInventory = TypicalIngredients.getTypicalInventory();
        assertEquals(inventoryFromFile, typicalIngredientInventory);
    }

    @Test
    public void toModelType_invalidIngredientFile_throwsUnitFormatException() throws Exception {
        JsonSerializableInventory dataFromFile = JsonUtil.readJsonFile(INVALID_INVENTORY_FILE,
                JsonSerializableInventory.class).get();
        assertThrows(UnitFormatException.class, dataFromFile::toModelType);
    }
}
