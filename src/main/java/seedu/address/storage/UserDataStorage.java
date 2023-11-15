package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.user.ReadOnlyUserData;

/**
 * Represents a storage for {@link seedu.address.model.AddressBook}.
 */
public interface UserDataStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getUserDataFilePath();

    /**
     * Returns UserData data as a {@link ReadOnlyUserData}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyUserData> readUserData() throws DataLoadingException;

    /**
     * @see #getUserDataFilePath()
     */
    Optional<ReadOnlyUserData> readUserData(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyUserData \} to the storage.
     * @param userData cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveUserData(ReadOnlyUserData userData) throws IOException;

    /**
     * @see #saveUserData(ReadOnlyUserData)
     */
    void saveUserData(ReadOnlyUserData userData, Path filePath) throws IOException;

}

