package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyConText;

/**
 * Represents a storage for {@link seedu.address.model.ConText}.
 */
public interface ConTextStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getConTextFilePath();

    /**
     * Returns ConText data as a {@link ReadOnlyConText}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyConText> readConText() throws DataLoadingException;

    /**
     * @see #getConTextFilePath()
     */
    Optional<ReadOnlyConText> readConText(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyConText} to the storage.
     * @param ConText cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveConText(ReadOnlyConText conText) throws IOException;

    /**
     * @see #saveConText(ReadOnlyConText)
     */
    void saveConText(ReadOnlyConText conText, Path filePath) throws IOException;

}
