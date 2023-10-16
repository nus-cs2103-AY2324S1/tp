package seedu.address.testutil;

import seedu.address.model.ConText;
import seedu.address.model.contact.Contact;

/**
 * A utility class to help with building ConText objects.
 * Example usage: <br>
 *     {@code ConText ab = new ConTextBuilder().withContact("John", "Doe").build();}
 */
public class ConTextBuilder {

    private ConText ConText;

    public ConTextBuilder() {
        ConText = new ConText();
    }

    public ConTextBuilder(ConText ConText) {
        this.ConText = ConText;
    }

    /**
     * Adds a new {@code Contact} to the {@code ConText} that we are building.
     */
    public ConTextBuilder withContact(Contact contact) {
        ConText.addContact(contact);
        return this;
    }

    public ConText build() {
        return ConText;
    }
}
