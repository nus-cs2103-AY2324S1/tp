package seedu.address.model.person;

import seedu.address.model.person.fields.Name;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Person {

    // Identity fields
    private final Name name;

    /**
     * Name must be present and not null.
     *
     * @param name The name of the person.
     */
    public Person(Name name) {
        assert name != null : "Name cannot be null";
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    public abstract boolean isSamePerson(Person otherPerson);

    public abstract boolean equals(Object other);

    public abstract int hashCode();

    public abstract String toString();

    public abstract String detailsToCopy();

}
