package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_AMY;
import static seedu.address.testutil.TypicalAppointments.ALEX_APPOINTMENT;
import static seedu.address.testutil.TypicalAppointments.ALEX_SECOND_APPOINTMENT;
import static seedu.address.testutil.TypicalAppointments.BERNICE_APPOINTMENT;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AppointmentBuilder;

public class AppointmentTest {

    @Test
    public void isSameAppointment() {
        // same object -> returns true
        assertTrue(ALEX_APPOINTMENT.isSameAppointment(ALEX_APPOINTMENT));

        // null -> returns false
        assertFalse(ALEX_APPOINTMENT.isSameAppointment(null));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Appointment alexAppointmentCopy = new AppointmentBuilder(ALEX_APPOINTMENT).build();
        assertTrue(ALEX_APPOINTMENT.equals(alexAppointmentCopy));

        // same object -> returns true
        assertTrue(ALEX_APPOINTMENT.equals(ALEX_APPOINTMENT));

        // null -> returns false
        assertFalse(ALEX_APPOINTMENT.equals(null));

        // different type -> returns false
        assertFalse(ALEX_APPOINTMENT.equals(5));

        // different appointment -> returns false
        assertFalse(ALEX_APPOINTMENT.equals(ALEX_SECOND_APPOINTMENT));
        assertFalse(ALEX_APPOINTMENT.equals(BERNICE_APPOINTMENT));

        // different name -> returns false
        Appointment editedAlexAppointment = new AppointmentBuilder(ALEX_APPOINTMENT).withName(VALID_NAME_BOB).build();
        assertFalse(ALEX_APPOINTMENT.equals(editedAlexAppointment));

        // different description -> returns false
        editedAlexAppointment = new AppointmentBuilder(ALEX_APPOINTMENT).withDescription(VALID_DESCRIPTION_BOB).build();
        assertFalse(ALEX_APPOINTMENT.equals(editedAlexAppointment));

        // different startTime and endTime-> returns false
        editedAlexAppointment = new AppointmentBuilder(ALEX_APPOINTMENT).withStartTime(VALID_START_TIME_AMY)
                .withEndTime(VALID_END_TIME_AMY).build();
        assertFalse(ALEX_APPOINTMENT.equals(editedAlexAppointment));

        // different date -> returns false
        editedAlexAppointment = new AppointmentBuilder(ALEX_APPOINTMENT).withDate(VALID_DATE_BOB).build();
        assertFalse(ALEX_APPOINTMENT.equals(editedAlexAppointment));

    }

    @Test
    public void toStringMethod() {
        String expected = Appointment.class.getCanonicalName()
                + "{student=" + ALEX_APPOINTMENT.getName() + ","
                + " description=" + ALEX_APPOINTMENT.getDescription() + ","
                + " date=" + ALEX_APPOINTMENT.getDate() + ","
                + " startTime=" + ALEX_APPOINTMENT.getStartTime() + ","
                + " endTime=" + ALEX_APPOINTMENT.getEndTime() + "}";
        assertEquals(expected, ALEX_APPOINTMENT.toString());
    }
}
