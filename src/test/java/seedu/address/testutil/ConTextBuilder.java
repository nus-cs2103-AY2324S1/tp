package seedu.address.testutil;

import seedu.address.model.ContactsManager;
import seedu.address.model.contact.Contact;

/**
 * A utility class to help with building ContactsManager objects.
 * Example usage: <br>
 *     {@code ContactsManager ab = new ConTextBuilder().withContact("John", "Doe").build();}
 */
public class ConTextBuilder {

    private ContactsManager contactsManager;

    public ConTextBuilder() {
        contactsManager = new ContactsManager();
    }

    public ConTextBuilder(ContactsManager contactsManager) {
        this.contactsManager = contactsManager;
    }

    /**
     * Adds a new {@code Contact} to the {@code ContactsManager} that we are building.
     */
    public ConTextBuilder withContact(Contact contact) {
        contactsManager.addContact(contact);
        return this;
    }

    public ContactsManager build() {
        return contactsManager;
    }
}
