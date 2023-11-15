package seedu.address.model.displayable;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;
/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */

public abstract class Person implements Displayable {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Priority priority;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.priority = new Priority("nil");
    }

    /**
     * Overloaded constructor for Person with an optional field for priority.
     * NOTE: This method is meant for temporal usage; Person is to be refactored to have only 1 constructor
     * which includes priority as an argument, with all test cases being refactored to match this change, after
     * the SetPriorityCommand has been implemented.
     * we can instead modify the AddBuyerCommandParser / AddSellerCommandParser to check if argMultimap contains
     * the respective prefixes or not, with only PrefixName being compulsory. Else, if argMultimap does not
     * contain the prefix for non-compulsory fields (ie phone, email etc.), simply construct Person with the
     * default null values for these non-compulsory fields.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Priority priority) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.priority = priority;
    }

    @Override
    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Priority getPriority() {
        return priority;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    @Override
    public boolean isSameDisplayable(Displayable displayable) {
        return ((displayable instanceof Person) && isSamePerson((Person) displayable));
    }

    /**
     * A partial equalsHelper for subclasses.
     * @param otherPerson the other displayable to compare against.
     * @return whether these fields are equal.
     */
    public boolean equalsHelper(Person otherPerson) {
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && tags.equals(otherPerson.tags)
                && priority.equals(otherPerson.priority);
    }

    /**
     * A partial string builder for subclasses.
     * @return a ToStringBuilder with loaded values, for subclasses to add onto.
     */
    public ToStringBuilder toStringBuild() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .add("priority", priority);
    }
    @Override
    public boolean isSimilarDisplayable(Displayable displayable) {
        if (displayable == this) {
            return true;
        }
        return getName().isSameNameFuzzyMatch(displayable.getName());
    }
}
