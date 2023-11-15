package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalRecipe.getTypicalRecipeBook;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.JsonUtil;
import seedu.address.model.RecipeBook;
import seedu.address.model.recipe.exceptions.RecipeStepFormatException;

public class JsonSerializableRecipeBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableRecipeBookTest");
    private static final Path TYPICAL_RECIPEBOOK_FILE = TEST_DATA_FOLDER.resolve("typicalRecipeBook.json");
    private static final Path INVALID_RECIPEBOOK_FILE = TEST_DATA_FOLDER.resolve("invalidRecipeBook.json");

    @Test
    public void toModelType_typicalRecipeBookFile_success() throws Exception {
        JsonSerializableRecipeBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_RECIPEBOOK_FILE,
                JsonSerializableRecipeBook.class).get();
        RecipeBook recipeBookFromFile = dataFromFile.toModelType();
        RecipeBook typicalRecipeBook = getTypicalRecipeBook();
        assertEquals(typicalRecipeBook, recipeBookFromFile);
    }

    @Test
    public void toModelType_invalidRecipeBookFile_throwsRecipeStepFormatException() throws Exception {
        JsonSerializableRecipeBook dataFromFile = JsonUtil.readJsonFile(INVALID_RECIPEBOOK_FILE,
                JsonSerializableRecipeBook.class).get();
        assertThrows(RecipeStepFormatException.class, dataFromFile::toModelType);
    }

}
