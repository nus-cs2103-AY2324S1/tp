package seedu.address.storage;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyDeck;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

/**
 * API of the Storage component
 */
public interface Storage extends DeckStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getDeckFilePath();

    @Override
    Optional<ReadOnlyDeck> readDeck() throws DataLoadingException;

    @Override
    void saveDeck(ReadOnlyDeck addressBook) throws IOException;
}
