package seedu.address.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.contact.Contact;



/**
 * API of the Model component.
 */
public interface Model {
    public ReadOnlyContacts getContacts();

    public void addContact(Contact contact);
    public boolean containsContact(Contact contact);
    public void updateContact(Contact old, Contact updated);
    public void removeContact(Contact contact);

    public Settings getSettings();

    public GuiSettings getGuiSettings();
    public void setGuiSettings(GuiSettings guiSettings);

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
