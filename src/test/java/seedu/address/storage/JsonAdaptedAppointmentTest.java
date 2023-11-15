package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointment.APPOINTMENT_1;
import static seedu.address.testutil.TypicalPatient.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.AppointmentTime;
import seedu.address.model.person.Ic;

public class JsonAdaptedAppointmentTest {

    private static final String INVALID_IC = "A1234567G";
    private static final String INVALID_PATIENT_IC = "S123Z";
    private static final String INVALID_APPOINTMENT_TIME = "123456";

    private static final String VALID_DOCTOR_IC = APPOINTMENT_1.getDoctor().toString();
    private static final String VALID_PATIENT_IC = APPOINTMENT_1.getPatient().toString();
    private static final String VALID_APPOINTMENT_TIME = APPOINTMENT_1.getAppointmentTime().toString();

    @Test
    public void toModelType_validPatientDetails_returnsPatient() throws Exception {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(BENSON);
        assertEquals(BENSON, patient.toModelType());
    }

    @Test
    public void toModelType_invalidPatientIc_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment =
                new JsonAdaptedAppointment(VALID_DOCTOR_IC, INVALID_IC, VALID_APPOINTMENT_TIME);
        String expectedMessage = Ic.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullPatientIc_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment =
                new JsonAdaptedAppointment(VALID_DOCTOR_IC, null, VALID_APPOINTMENT_TIME);
        String expectedMessage =
                String.format(JsonAdaptedAppointment.MISSING_FIELD_MESSAGE_FORMAT, Ic.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_invalidDoctorIc_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment =
                new JsonAdaptedAppointment(INVALID_IC, VALID_PATIENT_IC, VALID_APPOINTMENT_TIME);
        String expectedMessage = Ic.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullDoctorIc_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment =
                new JsonAdaptedAppointment(null, VALID_PATIENT_IC, VALID_APPOINTMENT_TIME);
        String expectedMessage =
                String.format(JsonAdaptedAppointment.MISSING_FIELD_MESSAGE_FORMAT, Ic.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_invalidAppointment_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment =
                new JsonAdaptedAppointment(VALID_DOCTOR_IC, VALID_PATIENT_IC, INVALID_APPOINTMENT_TIME);
        String expectedMessage = AppointmentTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullAppointment_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment =
                new JsonAdaptedAppointment(VALID_DOCTOR_IC, VALID_PATIENT_IC, null);
        String expectedMessage = String.format(JsonAdaptedAppointment.MISSING_FIELD_MESSAGE_FORMAT,
                AppointmentTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_duplicateIc_throwsIllegalValueException() {
        // same patient ic
        JsonAdaptedAppointment appointment =
                new JsonAdaptedAppointment(VALID_PATIENT_IC, VALID_PATIENT_IC, VALID_APPOINTMENT_TIME);
        String expectedMessage = JsonAdaptedAppointment.DUPLICATE_PATIENT_AND_DOCTOR_IC;
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
        // same doctor ic
        appointment =
                new JsonAdaptedAppointment(VALID_DOCTOR_IC, VALID_DOCTOR_IC, VALID_APPOINTMENT_TIME);
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

}
