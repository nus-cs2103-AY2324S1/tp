package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;

    // Data fields
    private final Set<Role> roles = new HashSet<>();
    private final Set<Contact> contacts = new HashSet<>();
    private final Set<Course> courses = new HashSet<>();
    private final Set<Tutorial> tutorials = new HashSet<>();
    private final Favourite favourite;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Set<Role> roles, Set<Contact> contacts, Set<Course> courses, Set<Tutorial> tutorials,
                  Favourite favourite) {
        requireAllNonNull(name, roles, contacts, courses, tutorials);
        this.name = name;
        this.roles.addAll(roles);
        this.contacts.addAll(contacts);
        this.courses.addAll(courses);
        this.tutorials.addAll(tutorials);
        this.favourite = favourite;
    }

    public Name getName() {
        return name;
    }

    /**
     * Returns an immutable roles set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Role> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    public Set<Contact> getContacts() {
        return Collections.unmodifiableSet(contacts);
    }

    public Set<Course> getCourses() {
        return Collections.unmodifiableSet(courses);
    }

    public Set<Tutorial> getTutorials() {
        return Collections.unmodifiableSet(tutorials);
    }

    public Favourite getFavourite() {
        return favourite;
    }

    /**
     * Favourites the person.
     */
    public void setFavourite() {
        favourite.setFavourite();
    }

    /**
     * Unfavourites the person.
     */
    public void setUnfavourite() {
        favourite.setUnfavourite();
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
                && roles.equals(otherPerson.roles)
                && contacts.equals(otherPerson.contacts)
                && courses.equals(otherPerson.courses)
                && tutorials.equals(otherPerson.tutorials);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, roles, contacts, courses, tutorials);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("roles", roles)
                .add("contacts", contacts)
                .add("courses", courses)
                .add("tutorials", tutorials)
                .toString();
    }

}
