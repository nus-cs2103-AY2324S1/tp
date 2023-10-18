package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.UniqueContactList;



/**
 * Mutable {@link Contact}s, both readable and writable.
 *
 * Duplicate {@link Contact}s are not allowed. Each {@link Contact} should be
 * unique as determined by {@link Contacts#contains}.
 */
public class Contacts implements ReadOnlyContacts {
    private UniqueContactList uniqueList = new UniqueContactList();

    /**
     * Constructs with no {@link Contact}s.
     */
    public Contacts() {}

    /**
     * Constructs a shallow clone of the specified {@link Contacts}.
     */
    public Contacts(Contacts contacts) {
        this.uniqueList.setContacts(contacts.uniqueList);
    }

    @Override
    public ObservableList<Contact> getUnmodifiableList() {
        return this.uniqueList.asUnmodifiableObservableList();
    }

    /**
     * Adds the specified {@link Contact}.
     *
     * The {@link Contact} must not already exist as determined by
     * {@link Contacts#contains}.
     */
    public void add(Contact contact) {
        this.uniqueList.add(contact);
    }

    /**
     * Returns whether there already exists a {@link Contact} that is the same
     * as the specified one.
     */
    public boolean contains(Contact contact) {
        return this.uniqueList.contains(contact);
    }

    /**
     * Replaces the old {@link Contact} with the new one.
     *
     * The new {@link Contact} must not already exist as determined by
     * {@link Contacts#contains}.
     *
     * @param old The old contact to replace.
     * @param updated The new, unique contact to take its place.
     */
    public void update(Contact old, Contact updated) {
        this.uniqueList.setContact(old, updated);
    }

    /**
     * Removes the specified {@link Contact}.
     */
    public void remove(Contact contact) {
        this.uniqueList.remove(contact);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("uniqueList", this.uniqueList)
                .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof also handles nulls
        if (!(other instanceof Contacts)) {
            return false;
        }
        Contacts otherContacts = (Contacts)other;

        return this.uniqueList.equals(otherContacts.uniqueList);
    }

    @Override
    public int hashCode() {
        return uniqueList.hashCode();
    }
}
