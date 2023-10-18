package seedu.address.testutil;

import seedu.address.model.ConText;
import seedu.address.model.contact.Contact;

/**
 * A utility class to help with building ConText objects.
 * Example usage: <br>
 *     {@code ConText ab = new ConTextBuilder().withContact("John", "Doe").build();}
 */
public class ConTextBuilder {

    private ConText conText;

    public ConTextBuilder() {
        conText = new ConText();
    }

    public ConTextBuilder(ConText conText) {
        this.conText = conText;
    }

    /**
     * Adds a new {@code Contact} to the {@code ConText} that we are building.
     */
    public ConTextBuilder withContact(Contact contact) {
        conText.addContact(contact);
        return this;
    }

    public ConText build() {
        return conText;
    }
}
