package seedu.cc.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_AGE_BOB;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_TAG_ASTHMA;
import static seedu.cc.testutil.Assert.assertThrows;
import static seedu.cc.testutil.TypicalPatients.ALICE;
import static seedu.cc.testutil.TypicalPatients.getTypicalClinicBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.cc.model.appointment.AppointmentEvent;
import seedu.cc.model.medicalhistory.MedicalHistoryEvent;
import seedu.cc.model.patient.Patient;
import seedu.cc.model.patient.exceptions.DuplicatePatientException;
import seedu.cc.testutil.PatientBuilder;

public class ClinicBookTest {

    private final ClinicBook clinicBook = new ClinicBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), clinicBook.getPatientList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> clinicBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyClinicBook_replacesData() {
        ClinicBook newData = getTypicalClinicBook();
        clinicBook.resetData(newData);
        assertEquals(newData, clinicBook);
    }

    @Test
    public void resetData_withDuplicatePatients_throwsDuplicatePatientException() {
        // Two persons with the same identity fields
        Patient editedAlice = new PatientBuilder(ALICE).withAge(VALID_AGE_BOB).withTags(VALID_TAG_ASTHMA)
                .build();
        List<Patient> newPatients = Arrays.asList(ALICE, editedAlice);
        ClinicBookStub newData = new ClinicBookStub(newPatients);

        assertThrows(DuplicatePatientException.class, () -> clinicBook.resetData(newData));
    }

    @Test
    public void hasPatient_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> clinicBook.hasPatient(null));
    }

    @Test
    public void hasPatient_personNotInClinicBook_returnsFalse() {
        assertFalse(clinicBook.hasPatient(ALICE));
    }

    @Test
    public void hasPatient_personInClinicBook_returnsTrue() {
        clinicBook.addPatient(ALICE);
        assertTrue(clinicBook.hasPatient(ALICE));
    }

    @Test
    public void hasPatient_personWithSameIdentityFieldsInClinicBook_returnsTrue() {
        clinicBook.addPatient(ALICE);
        Patient editedAlice = new PatientBuilder(ALICE).withAge(VALID_AGE_BOB).withTags(VALID_TAG_ASTHMA)
                .build();
        assertTrue(clinicBook.hasPatient(editedAlice));
    }

    @Test
    public void getPatientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> clinicBook.getPatientList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = ClinicBook.class.getCanonicalName() + "{patients=" + clinicBook.getPatientList() + "}";
        assertEquals(expected, clinicBook.toString());
    }

    /**
     * A stub ReadOnlyClinicBook whose persons list can violate interface constraints.
     */
    private static class ClinicBookStub implements ReadOnlyClinicBook {
        private final ObservableList<Patient> patients = FXCollections.observableArrayList();
        private final ObservableList<MedicalHistoryEvent> medicalHistoryEvents = FXCollections.observableArrayList();
        private final ObservableList<AppointmentEvent> appointmentEvents = FXCollections.observableArrayList();

        ClinicBookStub(Collection<Patient> patients) {
            this.patients.setAll(patients);
        }

        @Override
        public ObservableList<Patient> getPatientList() {
            return patients;
        }

        @Override
        public ObservableList<MedicalHistoryEvent> getClinicBookMedicalHistory() {
            return medicalHistoryEvents;
        }

        @Override
        public ObservableList<AppointmentEvent> getClinicBookAppointments() {
            return appointmentEvents;
        }
    }

}
