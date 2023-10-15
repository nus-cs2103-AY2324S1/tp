package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyDeck;
import seedu.address.model.ReadOnlyUserPrefs2;
import seedu.address.model.UserPrefs2;




/**
 * API of the Storage component
 */
public interface Storage2 extends DeckStorage, UserPrefsStorage2 {

    @Override
    Optional<UserPrefs2> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs2 userPrefs) throws IOException;

    @Override
    Path getDeckFilePath();

    @Override
    Optional<ReadOnlyDeck> readDeck() throws DataLoadingException;

    @Override
    void saveDeck(ReadOnlyDeck addressBook) throws IOException;

}
