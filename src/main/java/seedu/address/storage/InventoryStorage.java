package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyInventory;

/**
 * Represents a storage for {@link seedu.address.model.Inventory}.
 */
public interface InventoryStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getInventoryFilePath();

    /**
     * Returns Inventory data as a {@link ReadOnlyInventory}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyInventory> readInventory() throws DataLoadingException;

    /**
     * @see #getInventoryFilePath()
     */
    Optional<ReadOnlyInventory> readInventory(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyInventory} to the storage.
     * @param inventory cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveInventory(ReadOnlyInventory inventory) throws IOException;

    /**
     * @see #saveInventory(ReadOnlyInventory)
     */
    void saveInventory(ReadOnlyInventory inventory, Path filePath) throws IOException;

}
