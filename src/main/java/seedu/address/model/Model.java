package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.contact.Contact;



/**
 * API of the Model component.
 */
public interface Model {
    public Path getContactsPath();
    public void setContactsPath(Path contactsPath);

    public ReadOnlySettings getSettings();
    public void setSettings(ReadOnlySettings settings);

    public GuiSettings getGuiSettings();
    public void setGuiSettings(GuiSettings guiSettings);

    public ReadOnlyContacts getContacts();
    public void setContacts(ReadOnlyContacts contacts);

    public void addContact(Contact contact);
    public boolean containsContact(Contact contact);
    public void updateContact(Contact old, Contact updated);
    public void removeContact(Contact contact);

    /**
     * Filters the filtered contact list by the specified {@link Predicate}.
     */
    public void setContactsFilter(Predicate<Contact> predicate);

    /**
     * Returns an unmodifiable {@link ObservableList} of currently filtered
     * {@link Contact}s.
     */
    public ObservableList<Contact> getFilteredContactList();
}
