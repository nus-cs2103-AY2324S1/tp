package seedu.address.model.appointment;

import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class AppointmentTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(null,
                LocalDateTime.of(2023, 1, 1, 1, 1)));
    }

    @Test
    public void isValidAppointment() {

    }

}
