package seedu.application.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.application.commons.exceptions.DataLoadingException;
import seedu.application.model.ReadOnlyApplicationBook;
import seedu.application.model.ReadOnlyUserPrefs;
import seedu.application.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends ApplicationBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getApplicationBookFilePath();

    @Override
    Optional<ReadOnlyApplicationBook> readApplicationBook() throws DataLoadingException;

    @Override
    void saveApplicationBook(ReadOnlyApplicationBook applicationBook) throws IOException;

}
