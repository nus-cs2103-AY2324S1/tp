package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Record;

public class JsonAdaptedRecordTest {
    private final Patient expectedPatient = BENSON;
    @Test
    public void toModelType_validPatientDetails_returnsPatient() throws Exception {
        String expectedInitialObservations = "Initial observations";
        String expectedDiagnosis = "Diagnosis details";
        String expectedTreatmentPlan = "Treatment plan";

        // Create a JsonAdaptedRecord object with the above details
        JsonAdaptedRecord jsonAdaptedRecord = new JsonAdaptedRecord(
                expectedPatient, expectedInitialObservations, expectedDiagnosis, expectedTreatmentPlan);

        // Execution
        Record modelRecord = jsonAdaptedRecord.toModelType();

        // Verification
        assertEquals(expectedPatient, modelRecord.getPatient());
        assertEquals(expectedInitialObservations, modelRecord.getInitialObservations());
        assertEquals(expectedDiagnosis, modelRecord.getDiagnosis());
        assertEquals(expectedTreatmentPlan, modelRecord.getTreatmentPlan());
    }

    @Test
    public void toModelType_emptyFields_createsIncompleteRecord() throws Exception {
        // Test with empty strings
        JsonAdaptedRecord jsonAdaptedRecord = new JsonAdaptedRecord(
                expectedPatient, "", "", "");

        // Execution
        Record modelRecord = jsonAdaptedRecord.toModelType();

        // Verification
        assertEquals(expectedPatient, modelRecord.getPatient());
        assertEquals("", modelRecord.getInitialObservations());
        assertEquals("", modelRecord.getDiagnosis());
        assertEquals("", modelRecord.getTreatmentPlan());
    }

    @Test
    public void toModelType_nullPatient_createsRecordWithNullPatient() throws Exception {
        // Test with null patient
        JsonAdaptedRecord jsonAdaptedRecord = new JsonAdaptedRecord(
                null, "Observations", "Diagnosis", "Treatment");

        // Execution
        Record modelRecord = jsonAdaptedRecord.toModelType();

        // Verification
        assertNull(modelRecord.getPatient());
        assertEquals("Observations", modelRecord.getInitialObservations());
        assertEquals("Diagnosis", modelRecord.getDiagnosis());
        assertEquals("Treatment", modelRecord.getTreatmentPlan());
    }

    @Test
    public void toModelType_incompleteData_returnsRecord() throws Exception {
        Patient patient = expectedPatient;
        JsonAdaptedRecord jsonAdaptedRecord = new JsonAdaptedRecord(
                patient, "", null, "Treatment Plan");

        Record modelRecord = jsonAdaptedRecord.toModelType();

        assertEquals(patient, modelRecord.getPatient());
        assertEquals("", modelRecord.getInitialObservations());
        assertNull(modelRecord.getDiagnosis());
        assertEquals("Treatment Plan", modelRecord.getTreatmentPlan());
    }

    @Test
    public void equalsAndHashCode_validData_correctComparison() {
        Patient patient = expectedPatient;
        JsonAdaptedRecord record1 = new JsonAdaptedRecord(
                patient, "Observations", "Diagnosis", "Treatment Plan");
        JsonAdaptedRecord record2 = new JsonAdaptedRecord(
                patient, "Observations", "Diagnosis", "Treatment Plan");

        assertEquals(record1, record2);
        assertEquals(record1.hashCode(), record2.hashCode());
    }

    @Test
    public void equalsAndHashCode_differentData_incorrectComparison() {
        Patient patient1 = BENSON;
        Patient patient2 = ALICE;
        JsonAdaptedRecord record1 = new JsonAdaptedRecord(
                patient1, "Observations", "Diagnosis", "Treatment Plan");
        JsonAdaptedRecord record2 = new JsonAdaptedRecord(
                patient2, "Observations", "Diagnosis", "Treatment Plan");

        assertNotEquals(record1, record2);
        assertNotEquals(record1.hashCode(), record2.hashCode());
    }

}

