package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyRecipeBook;

/**
 * Represents a storage for {@link seedu.address.model.RecipeBook}
 */
public interface RecipeBookStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getRecipeBookFilePath();

    /**
     * Returns Inventory data as a {@link ReadOnlyRecipeBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyRecipeBook> readRecipeBook() throws DataLoadingException;

    /**
     * @see #getRecipeBookFilePath()
     */
    Optional<ReadOnlyRecipeBook> readRecipeBook(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyRecipeBook} to the storage.
     *
     * @param recipeBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveRecipeBook(ReadOnlyRecipeBook recipeBook) throws IOException;

    /**
     * @see #saveRecipeBook(ReadOnlyRecipeBook)
     */
    void saveRecipeBook(ReadOnlyRecipeBook recipeBook, Path filePath) throws IOException;
}
