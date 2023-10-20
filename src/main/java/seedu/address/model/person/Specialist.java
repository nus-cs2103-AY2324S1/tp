package seedu.address.model.person;

import java.util.Set;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Represents a Specialist in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Specialist extends Person {
    private final Specialty specialty;

    private final Location location;

    /**
     * Every field must be present and not null.
     */
    public Specialist(Name name, Phone phone, Email email, Location location, Set<Tag> tags, Specialty specialty) {
        super(name, phone, email, tags);
        this.location = location;
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        String stringToAdd = ", location=" + location + ", specialty=" + specialty;
        return StringUtil.addFieldToPersonToString(stringToAdd, super.toString());
    }

    /**
     * Returns true if the other object is a specialist and have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Specialist)) {
            return false;
        }

        Specialist otherSpecialist = (Specialist) other;

        return super.equals(other)
                && location.equals(otherSpecialist.location)
                && specialty.equals(otherSpecialist.specialty);
    }

    public Location getLocation() {
        return location;
    }
    public Specialty getSpecialty() {
        return specialty;
    }
}
