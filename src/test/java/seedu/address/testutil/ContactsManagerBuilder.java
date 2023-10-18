package seedu.address.testutil;

import seedu.address.model.ContactsManager;
import seedu.address.model.contact.Contact;

/**
 * A utility class to help with building ContactsManager objects.
 * Example usage: <br>
 *     {@code ContactsManager ab = new ContactsManagerBuilder().withContact("John", "Doe").build();}
 */
public class ContactsManagerBuilder {

    private ContactsManager contactsManager;

    public ContactsManagerBuilder() {
        contactsManager = new ContactsManager();
    }

    public ContactsManagerBuilder(ContactsManager contactsManager) {
        this.contactsManager = contactsManager;
    }

    /**
     * Adds a new {@code Contact} to the {@code ContactsManager} that we are building.
     */
    public ContactsManagerBuilder withContact(Contact contact) {
        contactsManager.addContact(contact);
        return this;
    }

    public ContactsManager build() {
        return contactsManager;
    }
}
