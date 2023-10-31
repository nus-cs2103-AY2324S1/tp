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
import static seedu.address.testutil.TypicalAppointments.DAVID_APPOINTMENT;
import static seedu.address.testutil.TypicalAppointments.JOHN_APPOINTMENT;

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

    @Test
    public void compareToMethod() {
        // ALEX_APPOINTMENT on "2023-10-31" from "12:00" to "13:00"
        // ALEX_SECOND_APPOINTMENT on "2023-11-16" from "14:00" to "15:00"

        // Different Dates, time don't matter
        assertEquals(ALEX_APPOINTMENT.compareTo(ALEX_SECOND_APPOINTMENT), -1);
        assertEquals(ALEX_APPOINTMENT.compareTo(ALEX_APPOINTMENT), 0);
        assertEquals(ALEX_SECOND_APPOINTMENT.compareTo(ALEX_APPOINTMENT), 1);

        // Same Date, compare time
        // DAVID_APPOINTMENT on "2023-10-31" from "11:00" to "12:00"
        // ALEX_APPOINTMENT on "2023-10-31" from "12:00" to "13:00"
        // JOHN_APPOINTMENT on "2023-10-31" from "13:00" to "14:00"
        assertEquals(DAVID_APPOINTMENT.compareTo(ALEX_APPOINTMENT), -1);
        assertEquals(JOHN_APPOINTMENT.compareTo(JOHN_APPOINTMENT), 0);
        assertEquals(JOHN_APPOINTMENT.compareTo(ALEX_APPOINTMENT), 1);
    }
}
