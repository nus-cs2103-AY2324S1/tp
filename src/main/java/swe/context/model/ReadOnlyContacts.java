package swe.context.model;

import javafx.collections.ObservableList;
import swe.context.model.contact.Contact;



/**
 * Read-only view of all {@link Contact}s.
 */
public interface ReadOnlyContacts {
    /**
     * Returns an unmodifiable {@link ObservableList} of all {@link Contact}s.
     */
    public ObservableList<Contact> getUnmodifiableList();
}
