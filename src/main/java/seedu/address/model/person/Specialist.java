package seedu.address.model.person;


import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Specialist in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Specialist extends Person {
    /**
     * Every field must be present and not null.
     */
    public Specialist(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        return super.toString();
    }
    @Override
    public boolean equals(Object other) {
        return super.equals(other);
    }

}
