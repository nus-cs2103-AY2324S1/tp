package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_THREE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;

public class JsonAdaptedAppointmentTest {

    private static final String VALID_DENTIST_ID = "1";
    private static final String VALID_PATIENT_ID = "1";
    private static final String VALID_DENTIST_NAME = "Alice";
    private static final String VALID_PATIENT_NAME = "May";
    private static final String VALID_START = "2023-10-19T16:00";
    private static final String VALID_DURATION = "PT10H30M";
    private static final String VALID_TREATMENT = "Braces";
    private static final String VALID_COST = "100";
    private static final String VALID_ID = "1";

    @Test
    public void toModelType_validAppointmentDetails_returnsAppointment() throws Exception {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(APPOINTMENT_THREE);
        assertEquals(APPOINTMENT_THREE, appointment.toModelType());
    }

    @Test
    public void toModelType_nullAppointmentId_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_DENTIST_ID, VALID_PATIENT_ID,
                VALID_DENTIST_NAME, VALID_PATIENT_NAME, VALID_START,
                VALID_DURATION, VALID_TREATMENT, VALID_COST, null);
        String expectedMessage = "id value does not exist!";
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullDentistId_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(null, VALID_PATIENT_ID,
                VALID_DENTIST_NAME, VALID_PATIENT_NAME, VALID_START,
                VALID_DURATION, VALID_TREATMENT, VALID_COST, VALID_ID);
        String expectedMessage = "Appointment's dentist id field is missing!";
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullPatientId_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_DENTIST_ID, null,
                VALID_DENTIST_NAME, VALID_PATIENT_NAME, VALID_START,
                VALID_DURATION, VALID_TREATMENT, VALID_COST, VALID_ID);
        String expectedMessage = "Appointment's patient id field is missing!";
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullDentistName_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_DENTIST_ID, VALID_PATIENT_ID,
                null, VALID_PATIENT_NAME, VALID_START,
                VALID_DURATION, VALID_TREATMENT, VALID_COST, VALID_ID);
        String expectedMessage = "Appointment's dentist name field is missing!";
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullPatientName_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_DENTIST_ID, VALID_PATIENT_ID,
                VALID_DENTIST_NAME, null, VALID_START,
                VALID_DURATION, VALID_TREATMENT, VALID_COST, VALID_ID);
        String expectedMessage = "Appointment's patient name field is missing!";
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullStart_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_DENTIST_ID, VALID_PATIENT_ID,
                VALID_DENTIST_NAME, VALID_PATIENT_NAME, null,
                VALID_DURATION, VALID_TREATMENT, VALID_COST, VALID_ID);
        String expectedMessage = "Appointment's start time field is missing!";
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullDuration_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_DENTIST_ID, VALID_PATIENT_ID,
                VALID_DENTIST_NAME, VALID_PATIENT_NAME, VALID_START,
                null, VALID_TREATMENT, VALID_COST, VALID_ID);
        String expectedMessage = "Appointment's duration field is missing!";
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullTreatment_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_DENTIST_ID, VALID_PATIENT_ID,
                VALID_DENTIST_NAME, VALID_PATIENT_NAME, VALID_START,
                VALID_DURATION, null, VALID_COST, VALID_ID);
        String expectedMessage = "Appointment's treatment field is missing!";
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullCost_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_DENTIST_ID, VALID_PATIENT_ID,
                VALID_DENTIST_NAME, VALID_PATIENT_NAME, VALID_START,
                VALID_DURATION, VALID_TREATMENT, null, VALID_ID);
        String expectedMessage = "Appointment's cost field is missing!";
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }
}
