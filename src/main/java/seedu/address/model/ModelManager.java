package seedu.address.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
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
        this.contacts = new Contacts(contacts);
        this.settings = new Settings(settings);

        this.filteredContacts = new FilteredList<>(this.contacts.getUnmodifiableList());
    }

    @Override
    public ReadOnlyContacts getContacts() {
        return this.contacts;
    }

    /**
     * Adds the specified {@link Contact}.
     *
     * Also resets the contacts filter.
     */
    @Override
    public void addContact(Contact contact) {
        this.contacts.add(contact);

        this.setContactsFilter(ModelManager.FILTER_NONE);
    }

    @Override
    public boolean containsContact(Contact contact) {
        return this.contacts.contains(contact);
    }

    @Override
    public void updateContact(Contact old, Contact updated) {
        this.contacts.update(old, updated);
    }

    @Override
    public void removeContact(Contact contact) {
        this.contacts.remove(contact);
    }

    @Override
    public void removeAllContacts() {
        this.contacts.removeAll();
    }

    @Override
    public Settings getSettings() {
        return this.settings;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return this.settings.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        this.settings.setGuiSettings(guiSettings);
    }

    @Override
    public void setContactsFilter(Predicate<Contact> predicate) {
        this.filteredContacts.setPredicate(predicate);
    }

    @Override
    public ObservableList<Contact> getFilteredContactList() {
        return this.filteredContacts;
    }

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
