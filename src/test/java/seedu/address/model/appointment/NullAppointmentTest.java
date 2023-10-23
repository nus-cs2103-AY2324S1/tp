package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class NullAppointmentTest {

    @Test
    public void getNullAppointment() {

        // Factory method should return same null appointment instance
        NullAppointment nullAppointment1 = NullAppointment.getNullAppointment();
        NullAppointment nullAppointment2 = NullAppointment.getNullAppointment();

        assertTrue(nullAppointment1 == nullAppointment2);
    }

    @Test
    public void equals() {

        NullAppointment nullAppointment1 = NullAppointment.getNullAppointment();
        Appointment appointment = new Appointment("Review Insurance",
                LocalDateTime.of(2023, 1, 1, 20, 00));

        assertFalse(nullAppointment1.equals(appointment));

        NullAppointment nullAppointment2 = NullAppointment.getNullAppointment();
        assertTrue(nullAppointment1.equals(nullAppointment2));
    }
}
