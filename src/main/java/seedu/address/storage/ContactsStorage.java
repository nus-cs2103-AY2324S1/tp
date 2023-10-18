package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.Contacts;
import seedu.address.model.ReadOnlyContacts;



/**
 * Represents a storage for {@link Contacts}.
 */
public interface ContactsStorage {
    /**
     * Returns the {@link Path} of the contacts storage file.
     */
    public Path getContactsPath();

    /**
     * Returns {@link Optional} {@link Contacts} by reading the contacts
     * storage file.
     *
     * If no file is found, returns {@link Optional.empty} instead.
     *
     * @throws DataLoadingException If loading data from the file fails.
     */
    public Optional<Contacts> readContacts() throws DataLoadingException;

    /**
     * Saves the specified {@link ReadOnlyContacts} to the contacts storage
     * file.
     *
     * @throws IOException If writing data to the file fails.
     */
    public void saveContacts(ReadOnlyContacts contacts) throws IOException;
}
