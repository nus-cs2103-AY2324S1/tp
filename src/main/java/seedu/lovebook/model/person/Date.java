package seedu.lovebook.model.person;

import static seedu.lovebook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.lovebook.commons.util.ToStringBuilder;
import seedu.lovebook.model.tag.Tag;

/**
 * Represents a Date in the lovebook book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Date {

    // Identity fields
    private final Name name;
    private final Age age;
    private final Gender gender;

    // Data fields
    private final Height height;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Date(Name name, Age age, Gender gender, Height height, Set<Tag> tags) {
        requireAllNonNull(name, age, gender, height, tags);
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.tags.addAll(tags);
    }

    /**
     * Constructor to incorporate User and Date class.
     */
    public Date() {
        this.name = null;
        this.age = null;
        this.gender = null;
        this.height = null;
    }

    public Name getName() {
        return name;
    }

    public Age getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public Height getHeight() {
        return height;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both dates have the same name.
     * This defines a weaker notion of equality between two dates.
     */
    public boolean isSamePerson(Date otherDate) {
        if (otherDate == this) {
            return true;
        }

        return otherDate != null
                && otherDate.getName().equals(getName());
    }

    /**
     * Returns true if both dates have the same identity and data fields.
     * This defines a stronger notion of equality between two dates.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Date)) {
            return false;
        }

        Date otherDate = (Date) other;
        return name.equals(otherDate.name)
                && age.equals(otherDate.age)
                && gender.equals(otherDate.gender)
                && height.equals(otherDate.height)
                && tags.equals(otherDate.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, age, gender, height, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("age", age)
                .add("gender", gender)
                .add("height", height)
                .add("tags", tags)
                .toString();
    }

}
