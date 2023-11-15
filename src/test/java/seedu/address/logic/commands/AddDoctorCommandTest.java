package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDoctor.WAYNE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Ic;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.testutil.DoctorBuilder;
//import seedu.address.testutil.PatientBuilder;

public class AddDoctorCommandTest {
    @Test
    public void constructor_nullDoctor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddDoctorCommand(null));
    }


    @Test
    public void execute_doctorAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Doctor validDoctor = new DoctorBuilder().build();

        CommandResult commandResult = new AddDoctorCommand(validDoctor).execute(modelStub);

        assertEquals(String.format(AddDoctorCommand.MESSAGE_SUCCESS, Messages.format(validDoctor)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validDoctor), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicateDoctor_throwsCommandException() {
        Doctor validDoctor = new DoctorBuilder().build();
        AddDoctorCommand addDoctorCommand = new AddDoctorCommand(validDoctor);
        ModelStub modelStub = new ModelStubWithDoctor(validDoctor);

        assertThrows(CommandException.class, AddDoctorCommand.MESSAGE_DUPLICATE_PERSON, () ->
                addDoctorCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Doctor bruce = new DoctorBuilder().withName("Bruce").build();
        Doctor barry = new DoctorBuilder().withName("Barry").build();
        AddDoctorCommand addBruceCommand = new AddDoctorCommand(bruce);
        AddDoctorCommand addBarryCommand = new AddDoctorCommand(barry);

        // same object -> returns true
        assertTrue(addBruceCommand.equals(addBruceCommand));

        // same values -> returns true
        AddDoctorCommand addBruceCommandCopy = new AddDoctorCommand(bruce);
        assertTrue(addBruceCommand.equals(addBruceCommandCopy));

        // different types -> returns false
        assertFalse(addBruceCommand.equals(1));

        // null -> returns false
        assertFalse(addBruceCommand.equals(null));

        // different doctor -> returns false
        assertFalse(addBruceCommand.equals(addBarryCommand));
    }

    @Test
    public void toStringMethod() {
        AddDoctorCommand addDoctorCommand = new AddDoctorCommand(WAYNE);
        String expected = AddDoctorCommand.class.getCanonicalName() + "{toAdd=" + WAYNE + "}";
        assertEquals(expected, addDoctorCommand.toString());
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
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Patient> getFilteredPatientList() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns an unmodifiable view of the filtered doctor list
         */
        @Override
        public ObservableList<Doctor> getFilteredDoctorList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undo() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redo() throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public boolean hasIc(Ic nric) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single patient.
     */
    private class ModelStubWithDoctor extends ModelStub {
        private final Doctor doctor;

        ModelStubWithDoctor(Doctor doctor) {
            requireNonNull(doctor);
            this.doctor = doctor;
        }

        @Override
        public boolean hasPerson(Person doctor) {
            requireNonNull(doctor);
            return this.doctor.isSamePerson(doctor);
        }
        @Override
        public boolean hasIc(Ic nric) {
            requireNonNull(nric);
            return this.doctor.getIc().equals(nric);
        }
    }

    /**
     * A Model stub that always accept the patient being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
        @Override
        public boolean hasIc(Ic nric) {
            return personsAdded.stream().anyMatch(person -> person.getIc().equals(nric));
        }
    }

}
