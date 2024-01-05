package seedu.address.model.consultation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentSet;

/**
 * Represents a Consultation with a date, time and specified students.
 */
public class Consultation {
    private LocalDate date;
    private LocalTime time;
    private StudentSet students;

    /**
     * Constructs an {@code Consultation}.
     *
     * @param date A valid date object.
     * @param time A valid time object.
     * @param students A set of students.
     */
    public Consultation(LocalDate date, LocalTime time, StudentSet students) {
        this.date = date;
        this.time = time;
        this.students = students;
    }

    /**
     * Adds a person to the StudentSet in a Consultation.
     *
     * @param student The student to be added into the StudentSet.
     * @return Returns itself after adding student.
     */
    public Consultation addStudent(Person student) {
        this.students.add(student);
        return this;
    }

    /**
     * Removes a person from the StudentSet in a Consultation.
     *
     * @param student The student to be removed from the StudentSet.
     * @return Returns itself after removing student.
     */
    public Consultation removeStudent(Person student) {
        this.students.remove(student);
        return this;
    }

    /**
     * Replaces a student in the consultation with another new student.
     *
     * @param target The target student to remove.
     * @param newStudent The new student to add.
     */
    public void replaceStudent(Person target, Person newStudent) {
        this.students.remove(target);
        this.students.add(newStudent);
    }

    /**
     * Check if StudentSet contains the {@code student}.
     */
    public boolean contains(Person student) {
        return this.students.contains(student);
    }

    /**
     * Checks if StudentSet has no students.
     *
     * @return Returns true if there is no students in the StudentSet and false if there are at least 1 student.
     */
    public boolean isEmpty() {
        return this.students.size() < 1;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Consultation)) {
            return false;
        }

        Consultation otherConsultation = (Consultation) other;
        return students.equals(otherConsultation.students)
                && date.equals(otherConsultation.date)
                && time.equals(otherConsultation.time);
    }

    /**
     * Returns true if both consultations have the same details.
     */
    public boolean isSameConsultation(Consultation other) {
        if (other == this) {
            return true;
        }

        return other != null
                && other.getDate().equals(this.getDate())
                && other.getTime().equals(this.getTime())
                && other.getStudents().equals(this.getStudents());
    }

    /**
     * Generates a human-readable representation of the consultation, including the date, time and the set of students.
     *
     * @return A string representation of the consultation.
     */
    public String getConsultationInfo() {
        String studentNames = "";
        if (students != null) {
            studentNames = students.toStudentNames();
        }
        return String.format("Date: %s; Time: %s; Students: %s", date, time, studentNames);
    }

    @Override
    public String toString() {
        return this.getConsultationInfo();
    }

    public LocalDate getDate() {
        return date;
    }
    public LocalTime getTime() {
        return time;
    }

    /**
     * Retrieves the set of students in this consultation.
     *
     * @return The set of students.
     */
    public StudentSet getStudents() {
        if (students == null) {
            students = new StudentSet();
        }
        return students.getStudents();
    }
    public Set<Name> getStudentsNames() {
        return students.stream().map(Person::getName).collect(Collectors.toSet());
    }
}
