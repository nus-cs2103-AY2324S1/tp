package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Represents a collection of students in a consultation/session. This class provides methods for managing
 * and manipulating the set of students within a consultation/session.
 */
public class StudentSet implements Iterable<Person> {
    public static final String MESSAGE_CONSTRAINTS =
            "Students must not be null";

    /** The set of students in the consultation/session. */
    public final Set<Person> students;

    /**
     * Constructs an empty StudentSet object.
     */
    public StudentSet() {
        this.students = new HashSet<>();
    }

    /**
     * Constructs a StudentSet object with a given set of students.
     *
     * @param students The set of students to initialize the object with.
     */
    public StudentSet(Set<Person> students) {
        requireNonNull(students);
        this.students = students;
    }

    /**
     * Constructs a StudentSet object with a single student.
     *
     * @param student The student to add to the consultation/session.
     */
    public StudentSet(Person student) {
        requireNonNull(student);
        this.students = new HashSet<>();
        this.students.add(student);
    }


    /**
     * Constructs a StudentSet object with an array of students.
     *
     * @param students The students to add to the consultation/session.
     */
    public StudentSet(Person ...students) {
        this.students = new HashSet<>();
        for (Person student : students) {
            this.students.add(student);
        }
    }

    /**
     * Adds a student to the set of students.
     *
     * @param student The student to add.
     */
    public void add(Person student) {
        this.students.add(student);
    }

    /**
     * Adds multiple students to the set of students.
     *
     * @param students The students to add.
     */
    public void addAll(StudentSet students) {
        this.students.addAll(students.getSetOfStudents());
    }

    /**
     * Removes a student from the consultation/session.
     *
     * @param student The student to remove.
     */
    public boolean remove(Person student) {
        return this.students.remove(student);
    }

    /**
     * Returns a string containing the names of the students in the consultation/session, sorted alphabetically.
     *
     * @return A string with student names.
     */
    public String toStudentNames() {
        List<Person> studentsArray = new ArrayList<>(students);
        studentsArray.sort(Comparator.comparing(p -> p.getName().toString()));
        StringBuilder studentNames = new StringBuilder();
        for (Person student : studentsArray) {
            studentNames.append(String.format("%s, ", student.getName()));
        }
        if (!students.isEmpty()) {
            studentNames.delete(studentNames.length() - 2, studentNames.length());
        }
        return studentNames.toString();
    }

    /**
     * Creates and returns a new StudentSet object with the same students as this one.
     *
     * @return A new SessionStudents object with a copy of the students.
     */
    public StudentSet getStudents() {
        StudentSet anotherSet = new StudentSet();
        for (Person student : students) {
            anotherSet.add(student);
        }
        return anotherSet;
    }

    /**
     * Returns the set of students stored.
     *
     * @return The set of Person stored in this StudentSet.
     */
    public Set<Person> getSetOfStudents() {
        return this.students;
    }

    /**
     * Checks if the consultation/session contains a specific student.
     *
     * @param student The student to check for existence.
     * @return True if the student exists in the consultation/session, false otherwise.
     */
    public boolean contains(Person student) {
        return students.contains(student);
    }

    /**
     * Provides a stream of students in the consultation/session.
     *
     * @return A stream of students.
     */
    public Stream<Person> stream() {
        return students.stream();
    }

    /**
     * Returns the number of students in the consultation/session.
     *
     * @return The number of students in the consultation/session.
     */
    public int size() {
        return students.size();
    }

    /**
     * Provides an iterator to iterate through the students in the consultation/session.
     *
     * @return An iterator for the students in the consultation/session.
     */
    @Override
    public Iterator<Person> iterator() {
        return students.iterator();
    }

    /**
     * Returns a string representation of the students in the consultation/session.
     *
     * @return A string representation of the students.
     */
    @Override
    public String toString() {
        return students.toString();
    }

    /**
     * Checks if this StudentSet object is equal to another object.
     *
     * @param other The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentSet)) {
            return false;
        }

        StudentSet otherStudentSet = (StudentSet) other;
        return students.equals(otherStudentSet.students);
    }

    /**
     * Returns the hash code of this StudentSet object.
     *
     * @return The hash code of the session students.
     */
    @Override
    public int hashCode() {
        return students.hashCode();
    }

}
