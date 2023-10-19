package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.IdentityCodeManager;
import seedu.address.model.tag.Tag;

/**
 * Represents a Developer in the address book.
 * Inherits from the Person class.
 */
public class Developer extends Person {

    /**
     * Constructs a new Developer instance.
     *
     * @param name    The name of the developer.
     * @param phone   The phone number of the developer.
     * @param email   The email of the developer.
     * @param address The address of the developer.
     * @param remark  Any remarks related to the developer.
     * @param tags    Any tags associated with the developer.
     */
    public Developer(Name name, Phone phone, Email email, Address address, Remark remark, Set<Tag> tags) {
        super(name, phone, email, address, remark, tags);
    }
}
