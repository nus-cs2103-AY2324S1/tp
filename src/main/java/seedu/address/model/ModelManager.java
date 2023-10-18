package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.contact.Contact;



/**
 * Implementation of the Model component.
 */
public class ModelManager implements Model {
    /**
     * {@link Predicate} that always evaluates to true. Using this as the filter
     * for the filtered contact list will not filter out any {@link Contact}s.
    */
    public static final Predicate<Contact> FILTER_NONE = (_contact) -> true;

    private final Contacts contacts;
    private final Settings settings;

    private final FilteredList<Contact> filteredContacts;

    /**
     * Constructs with default values.
     */
    public ModelManager() {
        this(new Contacts(), new Settings());
    }

    public ModelManager(ReadOnlyContacts contacts, ReadOnlySettings settings) {
        //TODO do we need to copy these objects? Eg Storage doesn't
        this.contacts = new Contacts(contacts);
        this.settings = new Settings(settings);

        this.filteredContacts = new FilteredList<>(this.contacts.getUnmodifiableList());
    }

    // @Override
    // public void setContacts(ReadOnlyContacts contactList) {
    //     this.contacts.overwrite(contacts);
    // }

    // @Override
    // public ReadOnlyContacts getContacts() {
    //     return contacts;
    // }

    // @Override
    // public boolean containsContact(Contact contact) {
    //     requireNonNull(contact);
    //     return contacts.contains(contact);
    // }

    // @Override
    // public void removeContact(Contact target) {
    //     contacts.remove(target);
    // }

    // @Override
    // public void addContact(Contact contact) {
    //     contacts.add(contact);
    //     setContactsFilter(ModelManager.FILTER_NONE);
    // }

    // @Override
    // public void updateContact(Contact target, Contact editedContact) {
    //     requireAllNonNull(target, editedContact);

    //     contacts.update(target, editedContact);
    // }

    // @Override
    // public void setSettings(ReadOnlySettings userPrefs) {
    //     requireNonNull(userPrefs);
    //     this.settings.overwrite(userPrefs);
    // }

    // @Override
    // public ReadOnlySettings getSettings() {
    //     return settings;
    // }

    // @Override
    // public GuiSettings getGuiSettings() {
    //     return settings.getGuiSettings();
    // }

    // @Override
    // public void setGuiSettings(GuiSettings guiSettings) {
    //     requireNonNull(guiSettings);
    //     settings.setGuiSettings(guiSettings);
    // }

    // @Override
    // public Path getContactsPath() {
    //     return settings.getContactsPath();
    // }

    // @Override
    // public void setContactsPath(Path conTextFilePath) {
    //     requireNonNull(conTextFilePath);
    //     settings.setContactsPath(conTextFilePath);
    // }

    // /**
    //  * Returns an unmodifiable view of the list of {@code Contact} backed by the internal list of
    //  * {@code versionedConText}
    //  */
    // @Override
    // public ObservableList<Contact> getFilteredContactList() {
    //     return filteredContacts;
    // }

    // @Override
    // public void setContactsFilter(Predicate<Contact> predicate) {
    //     requireNonNull(predicate);
    //     filteredContacts.setPredicate(predicate);
    // }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof also handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }
        ModelManager otherManager = (ModelManager)other;

        return (
            this.contacts.equals(otherManager.contacts)
            && this.settings.equals(otherManager.settings)
            && this.filteredContacts.equals(otherManager.filteredContacts)
        );
    }
}
