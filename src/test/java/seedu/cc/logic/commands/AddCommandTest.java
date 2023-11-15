package seedu.cc.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cc.testutil.Assert.assertThrows;
import static seedu.cc.testutil.TypicalPatients.ALICE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;
import seedu.cc.commons.core.GuiSettings;
import seedu.cc.logic.Messages;
import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.model.ClinicBook;
import seedu.cc.model.Model;
import seedu.cc.model.ReadOnlyClinicBook;
import seedu.cc.model.ReadOnlyUserPrefs;
import seedu.cc.model.appointment.AppointmentEvent;
import seedu.cc.model.appointment.Prescription;
import seedu.cc.model.medicalhistory.MedicalHistoryEvent;
import seedu.cc.model.patient.Patient;
import seedu.cc.testutil.PatientBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_patientAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPatientAdded modelStub = new ModelStubAcceptingPatientAdded();
        Patient validPatient = new PatientBuilder().build();

        CommandResult commandResult = new AddCommand(validPatient).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validPatient)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPatient), modelStub.patientsAdded);
    }

    @Test
    public void execute_duplicatePatient_throwsCommandException() {
        Patient validPatient = new PatientBuilder().build();
        AddCommand addCommand = new AddCommand(validPatient);
        ModelStub modelStub = new ModelStubWithPatient(validPatient);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PATIENT, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Patient alice = new PatientBuilder().withName("Alice").build();
        Patient bob = new PatientBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different patient -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddCommand addCommand = new AddCommand(ALICE);
        String expected = AddCommand.class.getCanonicalName() + "{toAdd=" + ALICE + "}";
        assertEquals(expected, addCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getClinicBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClinicBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClinicBook(ReadOnlyClinicBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyClinicBook getClinicBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePatient(Patient target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPatient(Patient target, Patient editedPatient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Patient> getFilteredPatientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPatientList(Predicate<Patient> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        //=========== Medical History Events =============================================================

        @Override
        public ObservableList<MedicalHistoryEvent> getFilteredMedicalHistoryEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMedicalHistoryEvent(Patient patient, MedicalHistoryEvent medicalHistoryEvent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override

        public void deleteMedicalHistoryEvent(Patient patient, MedicalHistoryEvent medicalHistoryEvent) {

        }

        public void setCurrentTab(int tabIndex) {
            System.out.println();
        }

        @Override
        public IntegerProperty currentTabProperty() {
            return null;
        }

        @Override
        public void listMedicalHistoryEvents(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMedicalHistoryEvent(Patient patient, MedicalHistoryEvent medicalHistoryEventToEdit,
                                           MedicalHistoryEvent editedMedicalHistoryEvent) {
            throw new AssertionError("This method should not be called.");
        }

        //=========== AppointmentEvent Operations =============================================================
        public ObservableList<AppointmentEvent> getFilteredAppointmentList() {
            throw new AssertionError("This method should not be called.");
        }

        public void addAppointmentEventToPatient(Patient patient, AppointmentEvent appointmentEvent) {
            throw new AssertionError("This method should not be called.");
        }

        public void listAppointmentsEventForPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        public void setAppointmentEventForPatient(Patient patient, AppointmentEvent appointmentEventToEdit,
                                           AppointmentEvent editedAppointmentEvent) {
            throw new AssertionError("This method should not be called.");
        }

        public void deleteAppointmentEventForPatient(Patient patient, AppointmentEvent appointmentEventToDelete) {
            throw new AssertionError("This method should not be called.");
        }

        //=========== Prescription Operations =============================================================
        public void addPrescriptionsToAppointmentEvent(Patient patient, AppointmentEvent appointmentEvent,
                                                       Set<Prescription> prescriptions) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single patient.
     */
    private class ModelStubWithPatient extends ModelStub {
        private final Patient patient;

        ModelStubWithPatient(Patient patient) {
            requireNonNull(patient);
            this.patient = patient;
        }

        @Override
        public boolean hasPatient(Patient patient) {
            requireNonNull(patient);
            return this.patient.isSamePerson(patient);
        }
    }

    /**
     * A Model stub that always accept the patient being added.
     */
    private class ModelStubAcceptingPatientAdded extends ModelStub {
        final ArrayList<Patient> patientsAdded = new ArrayList<>();

        @Override
        public boolean hasPatient(Patient patient) {
            requireNonNull(patient);
            return patientsAdded.stream().anyMatch(patient::isSamePerson);
        }

        @Override
        public void addPatient(Patient patient) {
            requireNonNull(patient);
            patientsAdded.add(patient);
        }

        @Override
        public ReadOnlyClinicBook getClinicBook() {
            return new ClinicBook();
        }
    }

}
