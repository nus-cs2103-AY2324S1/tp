package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_END;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_PRIORITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_START;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT1;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT2;
import static seedu.address.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AppointmentBuilder;

public class AppointmentTest {

    @Test
    public void equals() {
        // same values -> returns true
        Appointment appointmentCopy = new AppointmentBuilder(APPOINTMENT1).build();
        assertTrue(APPOINTMENT1.equals(appointmentCopy));

        // same object -> returns true
        assertTrue(APPOINTMENT1.equals(APPOINTMENT1));

        // null -> returns false
        assertFalse(APPOINTMENT1.equals(null));

        // different type -> returns false
        assertFalse(APPOINTMENT1.equals(5));

        // different person -> returns false
        assertFalse(APPOINTMENT1.equals(APPOINTMENT2));

        // different patient -> returns false
        Appointment editedAppointment = new AppointmentBuilder(APPOINTMENT1).withPatient(BENSON).build();
        assertFalse(APPOINTMENT1.equals(editedAppointment));

        // different appointment time -> returns false
        editedAppointment = new AppointmentBuilder(APPOINTMENT1)
                .withAppointmentTime(VALID_APPOINTMENT_START, VALID_APPOINTMENT_END).build();
        assertFalse(APPOINTMENT1.equals(editedAppointment));

        // different description -> returns false
        editedAppointment = new AppointmentBuilder(APPOINTMENT1)
                .withDescription(VALID_APPOINTMENT_DESCRIPTION).build();
        assertFalse(APPOINTMENT1.equals(editedAppointment));

        // different priority -> returns false
        editedAppointment = new AppointmentBuilder(APPOINTMENT1).withPriorityTag(VALID_APPOINTMENT_PRIORITY).build();
        assertFalse(APPOINTMENT1.equals(editedAppointment));
    }

    @Test
    public void toStringMethod() {
        String expected = Appointment.class.getCanonicalName() + "{patient=" + APPOINTMENT1.getPerson()
                + ", appointmentTime=" + APPOINTMENT1.getAppointmentTime()
                + ", description=" + APPOINTMENT1.getAppointmentDescription()
                + ", priority=" + APPOINTMENT1.getPriorityTag() + "}";
        assertEquals(expected, APPOINTMENT1.toString());
    }
}
