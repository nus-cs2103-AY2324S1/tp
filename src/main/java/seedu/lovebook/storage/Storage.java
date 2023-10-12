package seedu.lovebook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.lovebook.commons.exceptions.DataLoadingException;
import seedu.lovebook.model.ReadOnlyLoveBook;
import seedu.lovebook.model.ReadOnlyUserPrefs;
import seedu.lovebook.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends LoveBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getLoveBookFilePath();

    @Override
    Optional<ReadOnlyLoveBook> readLoveBook() throws DataLoadingException;

    @Override
    void saveLoveBook(ReadOnlyLoveBook loveBook) throws IOException;

}
