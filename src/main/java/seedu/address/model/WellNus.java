package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.UniqueAppointmentList;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class WellNus implements ReadOnlyWellNus {

    private final UniqueStudentList students;
    private final UniqueAppointmentList appointments;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
        appointments = new UniqueAppointmentList();
    }

    public WellNus() {}

    /**
     * Creates an WellNus using the Students in the {@code toBeCopied}
     */
    public WellNus(ReadOnlyWellNus toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Replaces the contents of the appointment list with {@code appointments}.
     * {@code appointments} must not contain duplicate appointments.
     */
    public void setAppointments(List<Appointment> appointments) {
        this.appointments.setAppointments(appointments);
    }

    /**
     * Resets the existing data of this {@code WellNus} with {@code newData}.
     */
    public void resetData(ReadOnlyWellNus newData) {
        requireNonNull(newData);
        setStudents(newData.getStudentList());
        setAppointments(newData.getAppointmentList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the student list.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a student to the student list.
     * The student must not already exist in the student list.
     */
    public void addStudent(Student p) {
        students.add(p);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the student list.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the
     * student list.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code WellNus}.
     * {@code key} must exist in the student list.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    //// appointment-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the student list.
     */
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointments.hasAppointment(appointment);
    }

    /**
     * Returns true if an appointment overlaps with {@code appointment} in the appointment list.
     *
     * @param appointment The appointment potentially added
     * @return true if overlaps, false otherwise.
     */
    public boolean hasOverlapsWithAppointments(Appointment appointment) {
        requireNonNull(appointment);
        return appointments.hasOverlapWith(appointment);
    }

    public boolean hasNoStudentForAppointment(Appointment appointment) {
        return !students.hasName(appointment.getName());
    }

    /**
     * Adds a person to the student list.
     * The person must not already exist in the student list.
     */
    public void addAppointment(Appointment a) {
        appointments.add(a);
    }

    /**
     * Removes {@code key} from this {@code WellNus}.
     * {@code key} must exist in the student list.
     */
    public void removeAppointment(Appointment key) {
        appointments.remove(key);
    }

    public void removeRelatedAppointments(Name key) {
        appointments.removeRelatedAppointments(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("students", students)
                .toString();
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Appointment> getAppointmentList() {
        return appointments.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof WellNus)) {
            return false;
        }

        WellNus otherWellNus = (WellNus) other;
        return students.equals(otherWellNus.students);
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }
}
