package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_2;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDoctor.CHERYL;
import static seedu.address.testutil.TypicalDoctor.DEREK;
import static seedu.address.testutil.TypicalPatient.AMY;
import static seedu.address.testutil.TypicalPatient.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.Appointment;

public class AppointmentTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(null, null, null));
    }

    @Test
    public void constructor_nullDoctor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(null, AMY,
                VALID_DATE_1));
    }
    @Test
    public void constructor_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(DEREK, null,
                VALID_DATE_1));
    }

    @Test
    public void constructor_nullAppointmentTime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(DEREK, AMY, null));
    }

    @Test
    public void secondConstructor_nullDoctor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(null, AMY,
                VALID_DATE_1, "Follow-Up"));
    }
    @Test
    public void secondConstructorr_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(DEREK, null,
                VALID_DATE_1, "Follow-Up"));
    }

    @Test
    public void secondConstructor_nullAppointmentTime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(DEREK, AMY,
                null, "Follow-Up"));
    }
    @Test
    public void constructor_nullStatus() { // should this throw a nullPointerException?
        assertThrows(NullPointerException.class, () -> new Appointment(DEREK, AMY, VALID_DATE_1, null));
    }

    @Test
    public void testGetDoctor() {
        Appointment newAppointment = new Appointment(DEREK, AMY, VALID_DATE_1);
        assertEquals(newAppointment.getDoctor(), DEREK);
    }

    @Test
    public void testGetPatient() {
        Appointment newAppointment = new Appointment(DEREK, AMY, VALID_DATE_1);
        assertEquals(newAppointment.getPatient(), AMY);
    }

    @Test
    public void testGetAppointmentTime() {
        Appointment newAppointment = new Appointment(DEREK, AMY, VALID_DATE_1);
        assertEquals(newAppointment.getAppointmentTime(), VALID_DATE_1);
    }
    @Test
    public void testChangeDoctor() {
        Appointment newAppointment = new Appointment(DEREK, AMY, VALID_DATE_1);
        newAppointment.changeDoctor(CHERYL);
        assertEquals(newAppointment.getDoctor(), CHERYL);
    }

    @Test
    public void testChangePatient() {
        Appointment newAppointment = new Appointment(DEREK, AMY, VALID_DATE_1);
        newAppointment.changePatient(BOB);
        assertEquals(newAppointment.getPatient(), BOB);
    }
    @Test
    public void testSetAppointmentTime() {
        Appointment newAppointment = new Appointment(DEREK, AMY, VALID_DATE_1);
        newAppointment.setAppointmentTime(VALID_DATE_2);
        assertEquals(newAppointment.getAppointmentTime(), VALID_DATE_2);
    }

    @Test
    public void testChangeStatus() {
        String newStatus = "Completed";
        Appointment newAppointment = new Appointment(DEREK, AMY, VALID_DATE_1);
        newAppointment.changeStatus(newStatus);
        assertEquals(newAppointment.getStatus(), newStatus);
    }

    @Test
    public void equals() {
        Appointment newAppointment = new Appointment(DEREK, AMY, VALID_DATE_1);

        // same values -> returns true
        assertTrue(newAppointment.equals(new Appointment(DEREK, AMY, VALID_DATE_1)));

        // same object -> returns true
        assertTrue(newAppointment.equals(newAppointment));

        // null -> returns false
        assertFalse(newAppointment.equals(null));

        // different types -> returns false
        assertFalse(newAppointment.equals(5.0f));

        // different values -> returns false
        assertFalse(newAppointment.equals(new Appointment(CHERYL, AMY, VALID_DATE_1)));
    }
}
