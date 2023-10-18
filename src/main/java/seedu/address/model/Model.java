package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.contact.Contact;



/**
 * API of the Model component.
 */
public interface Model {
    public ReadOnlyContacts getContacts();

    public Settings getSettings();

    public GuiSettings getGuiSettings();
    public void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns an unmodifiable {@link ObservableList} of currently filtered
     * {@link Contact}s.
     */
    public ObservableList<Contact> getFilteredContactList();

    //TODO which do we need to expose and where are they used
    // public void addContact(Contact contact);
    // public boolean containsContact(Contact contact);
    // public void updateContact(Contact old, Contact updated);
    // public void removeContact(Contact contact);

    /**
     * Filters the filtered contact list by the specified {@link Predicate}.
     */
    // public void setContactsFilter(Predicate<Contact> predicate);
}
