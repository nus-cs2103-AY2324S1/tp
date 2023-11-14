package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.availability.FreeTime;
import seedu.address.model.course.Course;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Telegram telegram;
    private final Set<Tag> tags = new HashSet<>();
    private final FreeTime freeTime;
    private final Set<Course> courses = new HashSet<>();
    private final Hour hour;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Telegram telegram, Set<Tag> tags,
                  FreeTime freeTime, Set<Course> courses, Hour hour) {
        requireAllNonNull(name, phone, email, telegram, tags, hour);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.telegram = telegram;
        this.tags.addAll(tags);
        this.freeTime = freeTime == null ? FreeTime.EMPTY_FREE_TIME : freeTime;
        this.courses.addAll(courses);
        this.hour = hour;
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

    public Telegram getTelegram() {
        return telegram;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public FreeTime getFreeTime() {
        return freeTime;
    }

    /**
     * Returns an immutable mod set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Course> getCourses() {
        return Collections.unmodifiableSet(courses);
    }

    public Hour getHour() {
        return hour;
    }


    /**
     * Updates the work hours of this person and returns a new Person object with the updated work hours.
     *
     * @param duration The number of hours to add or subtract from the work hours.
     * @return A new Person object with the updated work hours.
     */
    public Person updateHour(Integer duration) {
        Hour updatedHour = this.hour.addHour(duration);
        return new Person(this.name, this.phone, this.email, this.telegram, this.tags,
                this.freeTime, this.courses, updatedHour);
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
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && telegram.equals(otherPerson.telegram)
                && tags.equals(otherPerson.tags)
                && freeTime.equals(otherPerson.freeTime)
                && courses.equals(otherPerson.courses)
                && hour.equals(otherPerson.hour);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, telegram, tags, courses, hour);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("telegram", telegram)
                .add("tags", tags)
                .add("free time", freeTime)
                .add("courses", courses)
                .add("hours", hour)
                .toString();
    }
}
