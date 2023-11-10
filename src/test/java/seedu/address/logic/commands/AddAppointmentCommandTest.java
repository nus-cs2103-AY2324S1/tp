package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_DEREK;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointment.APPOINTMENT_1;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentTime;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Ic;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.testutil.AppointmentBuilder;

public class AddAppointmentCommandTest {

    @Test
    public void constructor_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAppointmentCommand(null));
    }

    @Test
    public void execute_sameDoctorAndPatient_throwsCommandException() {
        Appointment invalidAppointment = new AppointmentBuilder().withPatientIc(new Ic(VALID_NRIC_DEREK))
                .withDoctorIc(new Ic(VALID_NRIC_DEREK)).build();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(invalidAppointment);
        ModelStub modelStub = new ModelStub();

        assertThrows(CommandException.class, AddAppointmentCommand.MESSAGE_SAME_DOCTOR_AND_PATIENT, (
        ) -> addAppointmentCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        // default appointmentBuilder uses dic: Derek, pic: Bob, time: "2022-02-14 13:30"
        Appointment appointmentA = new AppointmentBuilder().build();
        Appointment appointmentB = new AppointmentBuilder().withPatientIc(new Ic(VALID_NRIC_AMY)).build();
        Appointment appointmentC = new AppointmentBuilder().withDoctorIc(new Ic(VALID_NRIC_AMY)).build();
        Appointment appointmentD =
                new AppointmentBuilder().withAppointmentTime(new AppointmentTime("2024-12-31 10:00")).build();
        AddAppointmentCommand addACommand = new AddAppointmentCommand(appointmentA);
        AddAppointmentCommand addBCommand = new AddAppointmentCommand(appointmentB);
        AddAppointmentCommand addCCommand = new AddAppointmentCommand(appointmentC);
        AddAppointmentCommand addDCommand = new AddAppointmentCommand(appointmentD);

        // same object -> returns true
        assertTrue(addACommand.equals(addACommand));

        // same values -> returns true
        AddAppointmentCommand addACommandCopy = new AddAppointmentCommand(appointmentA);
        assertTrue(addACommand.equals(addACommandCopy));

        // different types -> returns false
        assertFalse(addACommand.equals(1));

        // null -> returns false
        assertFalse(addACommand.equals(null));

        // different value for any field -> returns false
        assertFalse(addACommand.equals(addBCommand));
        assertFalse(addACommand.equals(addCCommand));
        assertFalse(addACommand.equals(addDCommand));
    }

    @Test
    public void toStringMethod() {
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(APPOINTMENT_1);
        String expected = AddAppointmentCommand.class.getCanonicalName() + "{toAdd=" + APPOINTMENT_1 + "}";
        assertEquals(expected, addAppointmentCommand.toString());
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
            return null;
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            return null;
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
    }

}
