package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_CHERYL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_DEREK;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentTime;

public class AppointmentTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(null, null, null));
    }

    @Test
    public void constructor_nullDoctor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(null, new Ic(VALID_NRIC_AMY),
                new AppointmentTime(VALID_DATE_1)));
    }

    @Test
    public void constructor_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(new Ic(VALID_NRIC_DEREK), null,
                new AppointmentTime(VALID_DATE_1)));
    }

    @Test
    public void constructor_nullAppointmentTime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, (
        ) -> new Appointment(new Ic(VALID_NRIC_DEREK), new Ic(VALID_NRIC_AMY), null));
    }

    @Test
    public void secondConstructor_nullDoctor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(null, new Ic(VALID_NRIC_AMY),
                new AppointmentTime(VALID_DATE_1)));
    }

    @Test
    public void secondConstructorr_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(new Ic(VALID_NRIC_DEREK), null,
                new AppointmentTime(VALID_DATE_1)));
    }

    @Test
    public void secondConstructor_nullAppointmentTime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(new Ic(VALID_NRIC_DEREK), new Ic(VALID_NRIC_AMY),
                null));
    }

    @Test
    public void testGetDoctor() {
        Appointment newAppointment = new Appointment(new Ic(VALID_NRIC_DEREK), new Ic(VALID_NRIC_AMY),
                new AppointmentTime(VALID_DATE_1));
        assertEquals(newAppointment.getDoctor(), new Ic(VALID_NRIC_DEREK));
    }

    @Test
    public void testGetPatient() {
        Appointment newAppointment = new Appointment(new Ic(VALID_NRIC_DEREK), new Ic(VALID_NRIC_AMY),
                new AppointmentTime(VALID_DATE_1));
        assertEquals(newAppointment.getPatient(), new Ic(VALID_NRIC_AMY));
    }

    @Test
    public void testGetAppointmentTime() {
        Appointment newAppointment = new Appointment(new Ic(VALID_NRIC_DEREK), new Ic(VALID_NRIC_AMY),
                new AppointmentTime(VALID_DATE_1));
        assertEquals(newAppointment.getAppointmentTime(), new AppointmentTime(VALID_DATE_1));
    }

    @Test
    public void testChangeDoctor() {
        Appointment newAppointment = new Appointment(new Ic(VALID_NRIC_DEREK), new Ic(VALID_NRIC_AMY),
                new AppointmentTime(VALID_DATE_1));
        newAppointment.changeDoctor(new Ic(VALID_NRIC_CHERYL));
        assertEquals(newAppointment.getDoctor(), new Ic(VALID_NRIC_CHERYL));
    }

    @Test
    public void testChangePatient() {
        Appointment newAppointment = new Appointment(new Ic(VALID_NRIC_DEREK), new Ic(VALID_NRIC_AMY),
                new AppointmentTime(VALID_DATE_1));
        newAppointment.changePatient(new Ic(VALID_NRIC_BOB));
        assertEquals(newAppointment.getPatient(), new Ic(VALID_NRIC_BOB));
    }

    @Test
    public void testSetAppointmentTime() {
        Appointment newAppointment = new Appointment(new Ic(VALID_NRIC_DEREK), new Ic(VALID_NRIC_AMY),
                new AppointmentTime(VALID_DATE_1));
        newAppointment.setAppointmentTime(new AppointmentTime(VALID_DATE_2));
        assertEquals(newAppointment.getAppointmentTime(), new AppointmentTime(VALID_DATE_2));
    }
    public void equals() {
        Appointment newAppointment = new Appointment(new Ic(VALID_NRIC_DEREK), new Ic(VALID_NRIC_AMY),
                new AppointmentTime(VALID_DATE_1));

        // same values -> returns true
        assertTrue(
                newAppointment.equals(new Appointment(new Ic(VALID_NRIC_DEREK), new Ic(VALID_NRIC_AMY),
                        new AppointmentTime(VALID_DATE_1))));

        // same object -> returns true
        assertTrue(newAppointment.equals(newAppointment));

        // null -> returns false
        assertFalse(newAppointment.equals(null));

        // different types -> returns false
        assertFalse(newAppointment.equals(5.0f));

        // different values -> returns false
        assertFalse(newAppointment.equals(
                new Appointment(new Ic(VALID_NRIC_CHERYL), new Ic(VALID_NRIC_AMY), new AppointmentTime(VALID_DATE_1))));
    }
}
