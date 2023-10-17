package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyDeck;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of Deck data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private DeckStorage deckStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code DeckStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(DeckStorage deckStorage, UserPrefsStorage userPrefsStorage) {
        this.deckStorage = deckStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ Deck methods ==============================

    @Override
    public Path getDeckFilePath() {
        return deckStorage.getDeckFilePath();
    }

    @Override
    public Optional<ReadOnlyDeck> readDeck() throws DataLoadingException {
        return readDeck(deckStorage.getDeckFilePath());
    }

    @Override
    public Optional<ReadOnlyDeck> readDeck(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return deckStorage.readDeck(filePath);
    }

    @Override
    public void saveDeck(ReadOnlyDeck deck) throws IOException {
        saveDeck(deck, deckStorage.getDeckFilePath());
    }

    @Override
    public void saveDeck(ReadOnlyDeck deck, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        deckStorage.saveDeck(deck, filePath);
    }

}
