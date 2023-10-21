package seedu.ccacommander.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.ccacommander.commons.exceptions.DataLoadingException;
import seedu.ccacommander.model.ReadOnlyCcaCommander;
import seedu.ccacommander.model.ReadOnlyUserPrefs;
import seedu.ccacommander.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends CcaCommanderStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyCcaCommander> readAddressBook() throws DataLoadingException;

    @Override
    void saveAddressBook(ReadOnlyCcaCommander addressBook) throws IOException;

}
