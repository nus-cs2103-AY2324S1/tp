package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.PATIENT_ALICE;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ModelStub;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.patients.Patient;
import seedu.address.model.treatment.TreatmentName;
import seedu.address.testutil.PatientBuilder;

public class AddPatientCommandTest {

    @Test
    public void constructor_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPatientCommand(null));
    }

    @Test
    public void execute_patientAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPatientAdded modelStub = new ModelStubAcceptingPatientAdded();
        Patient validPatient = new PatientBuilder().build();
        CommandResult commandResult = new AddPatientCommand(validPatient).execute(modelStub);

        assertEquals(
            String.format(AddPatientCommand.MESSAGE_SUCCESS, Messages.format(validPatient)),
            commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPatient), modelStub.patientAdded);
    }

    @Test
    public void execute_duplicatePatient_throwsCommandException() {
        Patient validPatient = new PatientBuilder().build();
        AddPatientCommand addPatientCommand = new AddPatientCommand(validPatient);
        ModelStub modelStub = new ModelStubWithPatient(validPatient);

        assertThrows(CommandException.class,
            AddPatientCommand.MESSAGE_DUPLICATE_PATIENT, () -> addPatientCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Patient alice = new PatientBuilder().withName("Alice").build();
        Patient bob = new PatientBuilder().withName("Bob").build();
        AddPatientCommand addAliceCommand = new AddPatientCommand(alice);
        AddPatientCommand addBobCommand = new AddPatientCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddPatientCommand addAliceCommandCopy = new AddPatientCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddPatientCommand addPatientCommand = new AddPatientCommand(PATIENT_ALICE);
        String expected =
            AddPatientCommand.class.getCanonicalName() + "{toAdd=" + PATIENT_ALICE + "}";
        assertEquals(expected, addPatientCommand.toString());
    }


    /**
     * A Model stub that contains a single person.
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

        @Override
        public boolean hasTreatmentName(TreatmentName treatmentName) {
            requireNonNull(treatmentName);
            return this.patient.getTreatmentName().isSameTreatmentName(treatmentName);
        }

    }

    /**
     * A Model stub that always accept the patient being added.
     */
    private class ModelStubAcceptingPatientAdded extends ModelStub {

        final ArrayList<Patient> patientAdded = new ArrayList<>();

        @Override
        public boolean hasPatient(Patient patient) {
            requireNonNull(patient);
            return patientAdded.stream().anyMatch(patient::isSamePatient);
        }

        @Override
        public boolean hasTreatmentName(TreatmentName treatmentName) {
            requireNonNull(treatmentName);
            return true;
        }

        @Override
        public void addPatient(Patient patient) {
            requireNonNull(patient);
            patientAdded.add(patient);
        }


        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
