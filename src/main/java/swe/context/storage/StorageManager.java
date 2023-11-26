package swe.context.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import swe.context.commons.exceptions.DataLoadingException;
import swe.context.model.Contacts;
import swe.context.model.ReadOnlyContacts;
import swe.context.model.ReadOnlySettings;
import swe.context.model.Settings;



/**
 * Implementation of the Storage component.
 *
 * Contains immutable {@link Path}s.
 */
public class StorageManager implements Storage {
    private ContactsStorage contactsStorage;
    private SettingsStorage settingsStorage;

    /**
     * Constructs with the specified values.
     */
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
