package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_AMY;
import static seedu.address.testutil.TypicalAppointments.ALICE_APPOINTMENT;
import static seedu.address.testutil.TypicalAppointments.ALICE_SECOND_APPOINTMENT;
import static seedu.address.testutil.TypicalAppointments.BENNY_APPOINTMENT;
import static seedu.address.testutil.TypicalAppointments.DAVID_APPOINTMENT;
import static seedu.address.testutil.TypicalAppointments.JOHN_APPOINTMENT;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AppointmentBuilder;

public class AppointmentTest {

    @Test
    public void isSameAppointment() {
        // same object -> returns true
        assertTrue(ALICE_APPOINTMENT.isSameAppointment(ALICE_APPOINTMENT));

        // null -> returns false
        assertFalse(ALICE_APPOINTMENT.isSameAppointment(null));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Appointment alexAppointmentCopy = new AppointmentBuilder(ALICE_APPOINTMENT).build();
        assertTrue(ALICE_APPOINTMENT.equals(alexAppointmentCopy));

        // same object -> returns true
        assertTrue(ALICE_APPOINTMENT.equals(ALICE_APPOINTMENT));

        // null -> returns false
        assertFalse(ALICE_APPOINTMENT.equals(null));

        // different type -> returns false
        assertFalse(ALICE_APPOINTMENT.equals(5));

        // different appointment -> returns false
        assertFalse(ALICE_APPOINTMENT.equals(ALICE_SECOND_APPOINTMENT));
        assertFalse(ALICE_APPOINTMENT.equals(BENNY_APPOINTMENT));

        // different name -> returns false
        Appointment editedAlexAppointment = new AppointmentBuilder(ALICE_APPOINTMENT).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE_APPOINTMENT.equals(editedAlexAppointment));

        // different description -> returns false
        editedAlexAppointment = new AppointmentBuilder(ALICE_APPOINTMENT)
                .withDescription(VALID_DESCRIPTION_BOB).build();
        assertFalse(ALICE_APPOINTMENT.equals(editedAlexAppointment));

        // different startTime and endTime-> returns false
        editedAlexAppointment = new AppointmentBuilder(ALICE_APPOINTMENT).withStartTime(VALID_START_TIME_AMY)
                .withEndTime(VALID_END_TIME_AMY).build();
        assertFalse(ALICE_APPOINTMENT.equals(editedAlexAppointment));

        // different date -> returns false
        editedAlexAppointment = new AppointmentBuilder(ALICE_APPOINTMENT).withDate(VALID_DATE_BOB).build();
        assertFalse(ALICE_APPOINTMENT.equals(editedAlexAppointment));

    }

    @Test
    public void toStringMethod() {
        String expected = Appointment.class.getCanonicalName()
                + "{student=" + ALICE_APPOINTMENT.getName() + ","
                + " description=" + ALICE_APPOINTMENT.getDescription() + ","
                + " date=" + ALICE_APPOINTMENT.getDate() + ","
                + " startTime=" + ALICE_APPOINTMENT.getStartTime() + ","
                + " endTime=" + ALICE_APPOINTMENT.getEndTime() + "}";
        assertEquals(expected, ALICE_APPOINTMENT.toString());
    }

    @Test
    public void compareToMethod() {
        // ALEX_APPOINTMENT on "2023-10-31" from "12:00" to "13:00"
        // ALEX_SECOND_APPOINTMENT on "2023-11-16" from "14:00" to "15:00"

        // Different Dates, time don't matter
        assertEquals(ALICE_APPOINTMENT.compareTo(ALICE_SECOND_APPOINTMENT), -1);
        assertEquals(ALICE_APPOINTMENT.compareTo(ALICE_APPOINTMENT), 0);
        assertEquals(ALICE_SECOND_APPOINTMENT.compareTo(ALICE_APPOINTMENT), 1);

        // Same Date, compare time
        // DAVID_APPOINTMENT on "2023-10-31" from "11:00" to "12:00"
        // ALEX_APPOINTMENT on "2023-10-31" from "12:00" to "13:00"
        // JOHN_APPOINTMENT on "2023-10-31" from "13:00" to "14:00"
        assertEquals(DAVID_APPOINTMENT.compareTo(ALICE_APPOINTMENT), -1);
        assertEquals(JOHN_APPOINTMENT.compareTo(JOHN_APPOINTMENT), 0);
        assertEquals(JOHN_APPOINTMENT.compareTo(ALICE_APPOINTMENT), 1);
    }
}
