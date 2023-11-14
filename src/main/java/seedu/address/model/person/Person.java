package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.socialmedialink.SocialMediaLink;
import seedu.address.model.tutorial.Tutorial;

/**
 * Represents a Person/Student in StudentConnect.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Email email;

    // Data fields
    private final Major major;
    private final Year year;
    private final Description description;
    private final Set<SocialMediaLink> socialMediaLinks = new HashSet<>();
    private final Set<Tutorial> tutorials = new HashSet<>();
    private final Nationality nationality;
    private final Gender gender;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Major major, Year year, Email email, Description description, Set<Tutorial> tutorials,
                  Set<SocialMediaLink> socialMediaLinks, Nationality nationality, Gender gender) {
        requireAllNonNull(name, major, year, email, description, tutorials, socialMediaLinks, nationality, gender);
        this.name = name;
        this.major = major;
        this.year = year;
        this.email = email;
        this.description = description;
        this.tutorials.addAll(tutorials);
        this.socialMediaLinks.addAll(socialMediaLinks);
        this.nationality = nationality;
        this.gender = gender;
    }

    public Name getName() {
        return name;
    }

    public Major getMajor() {
        return major;
    }

    public Year getYear() {
        return year;
    }

    public Email getEmail() {
        return email;
    }

    public Description getDescription() {
        return description;
    }

    public Set<Tutorial> getTutorials() {
        return Collections.unmodifiableSet(tutorials);
    }

    public Set<SocialMediaLink> getSocialMediaLinks() {
        return Collections.unmodifiableSet(socialMediaLinks);
    }

    public Nationality getNationality() {
        return nationality;
    }

    public Gender getGender() {
        return gender;
    }

    /**
     * Returns true if both persons have the same email.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
            && otherPerson.getEmail().equals(getEmail());
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
            && major.equals(otherPerson.major)
            && year.equals(otherPerson.year)
            && email.equals(otherPerson.email)
            && description.equals(otherPerson.description)
            && tutorials.equals(otherPerson.tutorials)
            && socialMediaLinks.equals(otherPerson.socialMediaLinks)
            && nationality.equals(otherPerson.nationality)
            && gender.equals(otherPerson.gender);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, major, year, email, description, tutorials, socialMediaLinks, nationality, gender);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("name", name)
            .add("major", major)
            .add("year", year)
            .add("email", email)
            .add("description", description)
            .add("tutorials", tutorials)
            .add("socialMediaLinks", socialMediaLinks)
            .add("nationality", nationality)
            .add("gender", gender)
            .toString();
    }
}
