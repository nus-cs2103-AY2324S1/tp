package seedu.address.storage;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyRecipeBook;

/**
 * A class to access RecipeBook data stored as a json file on the hard disk.
 */
public class JsonRecipeBookStorage implements RecipeBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonRecipeBookStorage.class);

    private Path filePath;

    public JsonRecipeBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getRecipeBookFilePath() {
        return this.filePath;
    }

    @Override
    public Optional<ReadOnlyRecipeBook> readRecipeBook() throws DataLoadingException {
        return readRecipeBook(this.filePath);
    }

    /**
     * Similar to {@link #readRecipeBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    @Override
    public Optional<ReadOnlyRecipeBook> readRecipeBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableRecipeBook> jsonRecipeBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableRecipeBook.class);
        if (!jsonRecipeBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonRecipeBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveRecipeBook(ReadOnlyRecipeBook recipeBook) throws IOException {
        saveRecipeBook(recipeBook, this.filePath);
    }

    /**
     * Similar to {@link #saveRecipeBook(ReadOnlyRecipeBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    @Override
    public void saveRecipeBook(ReadOnlyRecipeBook recipeBook, Path filePath) throws IOException {
        requireAllNonNull(recipeBook, filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableRecipeBook(recipeBook), filePath);
    }
}
