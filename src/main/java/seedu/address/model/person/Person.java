package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.person.fields.Name;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Person {

    // Identity fields
    private final Name name;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name) {
        requireAllNonNull(name);
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    public abstract boolean isSamePerson(Person otherPerson);

    public abstract boolean equals(Object other);

    public abstract int hashCode();

    public abstract String toString();

}
