package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.gradedtest.GradedTest;
import seedu.address.model.person.assignment.Assignment;
import seedu.address.model.person.assignment.AssignmentMap;
import seedu.address.model.person.assignment.AssignmentName;
import seedu.address.model.session.Session;
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
    private final TelegramHandle telegramHandle;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<GradedTest> gradedTests = new HashSet<>();
    private final AssignmentMap assignments;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, TelegramHandle telegramHandle,
                  Set<Tag> tags, Set<GradedTest> gradedTests) {
        requireAllNonNull(name, phone, email, telegramHandle, tags, gradedTests);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.telegramHandle = telegramHandle;
        this.tags.addAll(tags);
        this.gradedTests.addAll(gradedTests);
        this.assignments = new AssignmentMap();
    }

    /**
     * Creates a new Person object with a pre-set map of assignments.
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, TelegramHandle telegramHandle, Set<Tag> tags,
                  AssignmentMap assignments, Set<GradedTest> gradedTests) {
        requireAllNonNull(name, phone, email, telegramHandle, tags, assignments);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.telegramHandle = telegramHandle;
        this.tags.addAll(tags);
        this.gradedTests.addAll(gradedTests);
        this.assignments = assignments;
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

    public TelegramHandle getTelegramHandle() {
        return telegramHandle;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable gradedTests set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<GradedTest> getGradedTest() {
        return Collections.unmodifiableSet(gradedTests);
    }

    /**
     * Adds a student to a session and a session to a student.
     *
     * @param session The session to attend.
     */
    public void attendSession(Session session) {
        session.addStudent(this);
    }

    /**
     * Removes a student from a session and a session from a student.
     *
     * @param session The session to miss.
     */
    public void missSession(Session session) {
        session.removeStudent(this);
    }

    public AssignmentMap getAllAssignments() {
        return assignments;
    }

    public Assignment getAssignment(AssignmentName assignmentName) {
        return assignments.get(assignmentName);
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
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameName(Name otherName) {
        if (otherName == this.getName()) {
            return true;
        }

        return otherName.equals(getName());
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
                && telegramHandle.equals(otherPerson.telegramHandle)
                && tags.equals(otherPerson.tags)
                && assignments.equals(otherPerson.assignments)
                && gradedTests.equals(otherPerson.gradedTests);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, telegramHandle, tags, gradedTests);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("telegramHandle", telegramHandle)
                .add("tags", tags)
                .add("gradedTests", gradedTests)
                .toString();
    }

}
