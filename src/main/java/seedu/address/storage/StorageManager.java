package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.Contacts;
import seedu.address.model.ReadOnlyContacts;
import seedu.address.model.ReadOnlySettings;
import seedu.address.model.Settings;



/**
 * Implementation of the Storage component.
 */
public class StorageManager implements Storage {
    private ContactsStorage contactsStorage;
    private SettingsStorage settingsStorage;

    public StorageManager(
        ContactsStorage contactsStorage,
        SettingsStorage settingsStorage
    ) {
        this.contactsStorage = contactsStorage;
        this.settingsStorage = settingsStorage;
    }

    @Override
    public Path getContactsPath() {
        return this.contactsStorage.getContactsPath();
    }

    @Override
    public Optional<Contacts> readContacts() throws DataLoadingException {
        return this.contactsStorage.readContacts();
    }

    @Override
    public void saveContacts(ReadOnlyContacts contacts) throws IOException {
        this.contactsStorage.saveContacts(contacts);
    }

    @Override
    public Path getSettingsPath() {
        return this.settingsStorage.getSettingsPath();
    }

    @Override
    public Optional<Settings> readSettings() throws DataLoadingException {
        return this.settingsStorage.readSettings();
    }

    @Override
    public void saveSettings(ReadOnlySettings settings) throws IOException {
        this.settingsStorage.saveSettings(settings);
    }
}
