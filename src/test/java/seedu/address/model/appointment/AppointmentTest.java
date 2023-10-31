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
import static seedu.address.testutil.TypicalAppointments.BERNICE_APPOINTMENT;

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
        assertFalse(ALICE_APPOINTMENT.equals(BERNICE_APPOINTMENT));

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
}
