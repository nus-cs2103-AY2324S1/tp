package networkbook.model.person;

import static networkbook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import networkbook.commons.util.ToStringBuilder;
import networkbook.model.tag.Tag;
import networkbook.model.util.Identifiable;
import networkbook.model.util.UniqueList;

/**
 * Represents a Person in the network book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person implements Identifiable<Person> {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final UniqueList<Email> emails;

    // Data fields
    private final UniqueList<Link> links;
    private final GraduatingYear graduatingYear;
    private final Course course;
    private final Specialisation specialisation;
    private final Set<Tag> tags = new HashSet<>();
    private final Priority priority;

    /**
     * Name must be present and not null.
     * Other fields are nullable.
     */
    public Person(Name name,
                  Phone phone,
                  UniqueList<Email> emails,
                  UniqueList<Link> links,
                  GraduatingYear graduatingYear,
                  Course course,
                  Specialisation specialisation,
                  Set<Tag> tags,
                  Priority priority) {
        requireAllNonNull(name);
        this.name = name;
        this.phone = phone;
        this.emails = emails.copy();
        this.links = links.copy();
        this.graduatingYear = graduatingYear;
        this.course = course;
        this.specialisation = specialisation;
        this.tags.addAll(tags);
        this.priority = priority;
    }

    public Name getName() {
        return name;
    }

    public Optional<Phone> getPhone() {
        return Optional.ofNullable(phone);
    }

    public UniqueList<Email> getEmails() {
        return emails.copy();
    }
    public UniqueList<Link> getLinks() {
        return links.copy();
    }
    public Optional<GraduatingYear> getGraduatingYear() {
        return Optional.ofNullable(graduatingYear);
    }
    public Optional<Course> getCourse() {
        return Optional.ofNullable(course);
    }
    public Optional<Specialisation> getSpecialisation() {
        return Optional.ofNullable(specialisation);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Optional<Priority> getPriority() {
        return Optional.ofNullable(priority);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSame(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns string for Json storage.
     * However, a person cannot be converted to a simple string for Json storage.
     * Hence, this method is unsupported here.
     */
    public String getValue() {
        throw new UnsupportedOperationException("Person does not have String representation for Json storage");
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
                && Objects.equals(phone, otherPerson.phone)
                && Objects.equals(emails, otherPerson.emails)
                && Objects.equals(links, otherPerson.links)
                && Objects.equals(graduatingYear, otherPerson.graduatingYear)
                && Objects.equals(course, otherPerson.course)
                && Objects.equals(specialisation, otherPerson.specialisation)
                && tags.equals(otherPerson.tags)
                && Objects.equals(priority, otherPerson.priority);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, emails, links, graduatingYear, course, specialisation, tags, priority);
    }

    @Override
    public String toString() {
        ToStringBuilder tsb = new ToStringBuilder(this)
                .add("name", name);
        if (phone != null) {
            tsb.add("phone", phone);
        }
        if (!Objects.equals(emails, new UniqueList<Email>())) {
            tsb.add("emails", emails);
        }
        if (!Objects.equals(links, new UniqueList<Link>())) {
            tsb.add("links", links);
        }
        if (graduatingYear != null) {
            tsb.add("graduating year", graduatingYear);
        }
        if (course != null) {
            tsb.add("course", course);
        }
        if (specialisation != null) {
            tsb.add("specialisation", specialisation);
        }
        if (!tags.equals(Collections.emptySet())) {
            tsb.add("tags", tags);
        }
        if (priority != null) {
            tsb.add("priority", priority);
        }
        return tsb.toString();
    }

}
