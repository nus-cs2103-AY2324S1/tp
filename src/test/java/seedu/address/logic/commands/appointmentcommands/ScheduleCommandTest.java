package seedu.address.logic.commands.appointmentcommands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.VALID_END_ONE;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.VALID_END_TWO;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.VALID_START_ONE;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.VALID_START_TWO;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.history.UserHistoryManager;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.AppointmentBuilder;

public class ScheduleCommandTest {
    @Test
    public void constructor_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ScheduleCommand(null, null));
    }

    @Test
    public void equals() {

        Name testName1 = new Name("Test Name1");
        Name testName2 = new Name("Test Name2");

        Appointment appointment1 = new AppointmentBuilder().withAppointmentTime(VALID_START_ONE, VALID_END_ONE).build();
        Appointment appointment2 = new AppointmentBuilder().withAppointmentTime(VALID_START_TWO, VALID_END_TWO).build();
        ScheduleCommand scheduleCommand1 = new ScheduleCommand(appointment1, testName1);
        ScheduleCommand scheduleCommand2 = new ScheduleCommand(appointment2, testName2);

        ScheduleCommand scheduleCommandSameTimeDifferentName1 = new ScheduleCommand(appointment1, testName1);
        ScheduleCommand scheduleCommandSameTimeDifferentName2 = new ScheduleCommand(appointment1, testName2);

        // compare with different type - false
        assertFalse(scheduleCommand1.equals("Test"));

        // null test - false
        assertFalse(scheduleCommand1.equals(null));

        // compares same appointment timing but different names (Patients) - false
        assertFalse(scheduleCommandSameTimeDifferentName1.equals(scheduleCommandSameTimeDifferentName2));

        // compares AddCommand1 with itself - true
        assertTrue(scheduleCommand1.equals(scheduleCommand1));

        assertEquals(scheduleCommand2, scheduleCommand2);

        // compares 2 different Schedule Commands - false
        assertFalse(scheduleCommand1.equals(scheduleCommand2));

        // compares 2 different objects with same details - true
        ScheduleCommand copyOfScheduleCommand1 = new ScheduleCommand(appointment1, testName1);
        assertTrue(scheduleCommand1.equals(copyOfScheduleCommand1));

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
        public void addAppointment(Appointment appointment) {
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
        public boolean hasPerson(Name name) {
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
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAppointment(Appointment target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAppointment(Appointment target, Appointment editedAppointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public UserHistoryManager getUserHistoryManager() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoHistory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoHistory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that always accepts the appointment being added.
     */
    private class ModelStubAcceptingAppointmentAdded extends ModelStub {
        private final ArrayList<Appointment> appointments = new ArrayList<>();

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return appointments.stream().anyMatch(appointment::isSameAppointment);
        }

        @Override
        public void addAppointment(Appointment appointment) {
            requireNonNull(appointment);
            appointments.add(appointment);
        }
    }

    /**
     * A Model stub containing appointments
     */
    private class ModelStubContainingAppointments extends ModelStub {
        private final Appointment appointment;

        ModelStubContainingAppointments(Appointment appointment) {
            requireNonNull(appointment);
            this.appointment = appointment;
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return this.appointment.isSameAppointment(appointment);
        }
    }
}
