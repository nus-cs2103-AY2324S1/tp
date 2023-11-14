package networkbook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import networkbook.commons.exceptions.DataLoadingException;
import networkbook.commons.exceptions.NullValueException;
import networkbook.model.ReadOnlyNetworkBook;
import networkbook.model.ReadOnlyUserPrefs;
import networkbook.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends NetworkBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException, NullValueException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getNetworkBookFilePath();

    @Override
    Optional<ReadOnlyNetworkBook> readNetworkBook() throws DataLoadingException, NullValueException;

    @Override
    void saveNetworkBook(ReadOnlyNetworkBook networkBook) throws IOException;

}
