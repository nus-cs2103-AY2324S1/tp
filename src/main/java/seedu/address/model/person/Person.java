package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;
import seedu.address.model.week.Week;

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
    private final ID id;
    private final Set<Tag> tags = new HashSet<>();
    private final List<Attendance> attendanceRecords = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, ID id, Set<Tag> tags, List<Attendance> attendanceRecords) {
        requireAllNonNull(name, phone, email, id, tags, attendanceRecords);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.id = id;
        this.tags.addAll(tags);
        this.attendanceRecords.addAll(attendanceRecords);
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

    public ID getId() {
        return id;
    }


    public List<Attendance> getAttendanceRecords() {
        return attendanceRecords;
    }

    /**
     * Retrieves the Attendance object for the current week, if it exists.
     *
     * @return An Optional containing the Attendance object for the current week, or an empty Optional if not found.
     */
    public Optional<Attendance> getAttendanceForSpecifiedWeek(Week week) {
        return attendanceRecords.stream()
                .filter(attendance -> attendance.getWeek().equals(week))
                .findFirst();
    }


    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same id.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.id.equals(this.id);
    }

    public void addAttendance(Attendance attendance) {
        attendanceRecords.add(attendance);
    }

    /**
     * Returns a string representation of the number of tutorials that the person attended and missed.
     * @return number of tutorials attended, total tutorials, number of tutorials missed
     */
    public String getTalliedAttendance() {
        int tutorialsAttended = 0;
        int totalTutorials = attendanceRecords.size();

        for (int i = 0; i < totalTutorials; i++) {
            if (attendanceRecords.get(i).isPresent()) {
                tutorialsAttended++;
            }
        }

        if (totalTutorials == 0) {
            return "Attendance : No attendance records.";
        }
        return "Attendance : " + tutorialsAttended + " / " + totalTutorials + " ("
                + (totalTutorials - tutorialsAttended) + " tutorials missed)";
    }

    /**
     * Add two sets of AttendanceRecords to a specified person.
     * @param thisAttendanceRecords First set of Attendance Records to add
     * @param otherAttendanceRecords Second set of Attendance Records to add
     * @param newPerson Person to add the Attendance Records to
     */
    public void mergeAttendanceRecords(List<Attendance> thisAttendanceRecords, List<Attendance> otherAttendanceRecords,
                                       Person newPerson) {
        HashSet<Integer> attendanceWeeks = new HashSet<>();
        for (Attendance a : thisAttendanceRecords) {
            attendanceWeeks.add(a.getWeek().getWeekNumber());
        }
        for (Attendance b : otherAttendanceRecords) {
            if (!attendanceWeeks.contains(b.getWeek().getWeekNumber())) {
                attendanceWeeks.add(b.getWeek().getWeekNumber());
                newPerson.addAttendance(b);
            }
        }
    }

    /**
     * Create a new Person based on the information of this person and another person.
     * @param otherPerson The other set of information to use
     * @return A new person that contains the information of this person and otherPerson
     */
    public Person mergePersons(Person otherPerson) {
        Name name = this.name != null ? this.name : otherPerson.name;
        Phone phone = this.phone != null ? this.phone : otherPerson.phone;
        Email email = this.email != null ? this.email : otherPerson.email;
        ID id = this.id != null ? this.id : otherPerson.id;
        Set<Tag> tags = new HashSet<>();
        tags.addAll(this.tags);
        tags.addAll(otherPerson.tags);
        Person newPerson = new Person(name, phone, email, id, tags, this.attendanceRecords);
        newPerson.mergeAttendanceRecords(this.attendanceRecords, otherPerson.attendanceRecords, newPerson);
        return newPerson;
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
                && id.equals(otherPerson.id)
                && tags.equals(otherPerson.tags)
                && attendanceRecords.equals(otherPerson.attendanceRecords);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, id, tags, attendanceRecords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("id", id)
                .add("tutorial groups", tags)
                .toString();
    }

}
