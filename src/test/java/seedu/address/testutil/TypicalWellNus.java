package seedu.address.testutil;

import static seedu.address.testutil.TypicalAppointments.getTypicalAppointments;
import static seedu.address.testutil.TypicalStudents.getTypicalStudents;

import seedu.address.model.WellNus;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.student.Student;


/**
 * A utility class return a {@code WellNUS} object to be used in tests.
 */
public class TypicalWellNus {

    private TypicalWellNus() {} // prevents instantiation

    public static WellNus getTypicalWellNus() {
        WellNus ab = new WellNus();
        for (Appointment appointment : getTypicalAppointments()) {
            ab.addAppointment(appointment);
        }
        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }
        return ab;
    }
}
