package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.student.Student;

/**
 * Unmodifiable view of an wellnus storage
 */
public interface ReadOnlyWellNus {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

    ObservableList<Appointment> getAppointmentList();

}
