package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.displayable.DisplayableListViewItem;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person implements DisplayableListViewItem {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email) {
        requireAllNonNull(name, phone, email);
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
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
    public String getDisplayTitle() {
        return getName().toString();
    }

    @Override
    public String getDisplayFirstText() {
        return getPhone().toString();
    }

    @Override
    public String getDisplaySecondText() {
        return getEmail().toString();
    }

    @Override
    public boolean isSameItem(DisplayableListViewItem displayableListViewItem) {
        if (displayableListViewItem == this) {
            return true;
        }

        if (!(displayableListViewItem instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) displayableListViewItem;

        return isSamePerson(otherPerson);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .toString();
    }

}
