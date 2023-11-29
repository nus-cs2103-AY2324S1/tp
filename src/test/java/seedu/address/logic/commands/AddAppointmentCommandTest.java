package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_ONE;
import static seedu.address.testutil.TypicalTreatments.BRACES;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ModelStub;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.appointments.Appointment;
import seedu.address.model.person.dentist.Dentist;
import seedu.address.model.person.patients.Patient;
import seedu.address.model.treatment.Treatment;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.DentistBuilder;
import seedu.address.testutil.PatientBuilder;
import seedu.address.ui.CalendarWindow;


public class AddAppointmentCommandTest {

    @Test
    public void constructor_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAppointmentCommand(null));
    }

    @Test
    public void execute_appointmentAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingAppointmentAdded modelStub = new ModelStubAcceptingAppointmentAdded();
        Appointment validAppointment = new AppointmentBuilder().build();
        AddAppointmentCommand command = new AddAppointmentCommand(validAppointment);

        CalendarWindow mockCalendarWindow = mock(CalendarWindow.class);
        command.setCalendarWindow(mockCalendarWindow);

        CommandResult commandResult = command.execute(modelStub);

        assertEquals(String.format(AddAppointmentCommand.MESSAGE_SUCCESS, Messages.format(validAppointment)),
                commandResult.getFeedbackToUser());

        assertEquals(Arrays.asList(validAppointment), modelStub.appointmentAdded);
    }

    @Test
    public void execute_duplicateAppointment_throwsCommandException() {
        Appointment validAppointment = new AppointmentBuilder().build();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(validAppointment);

        CalendarWindow mockCalendarWindow = mock(CalendarWindow.class);
        addAppointmentCommand.setCalendarWindow(mockCalendarWindow);

        ModelStub modelStub = new ModelStubWithAppointment(validAppointment);

        assertThrows(CommandException.class,
                AddAppointmentCommand.MESSAGE_CLASHING_DENTIST, () -> addAppointmentCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Appointment one = new AppointmentBuilder().withDentistId("1").build();
        Appointment two = new AppointmentBuilder().withDentistId("2").build();
        AddAppointmentCommand addOneCommand = new AddAppointmentCommand(one);
        AddAppointmentCommand addTwoCommand = new AddAppointmentCommand(two);
        CalendarWindow mockCalendarWindow = mock(CalendarWindow.class);
        addOneCommand.setCalendarWindow(mockCalendarWindow);
        addTwoCommand.setCalendarWindow(mockCalendarWindow);

        // same object -> returns true
        assertTrue(addOneCommand.equals(addOneCommand));

        // same values -> returns true
        AddAppointmentCommand addOneCommandCopy = new AddAppointmentCommand(one);
        assertTrue(addOneCommand.equals(addOneCommandCopy));

        // different types -> returns false
        assertFalse(addOneCommand.equals(1));

        // null -> returns false
        assertFalse(addOneCommand.equals(null));

        // different appointments -> returns false
        assertFalse(addOneCommand.equals(addTwoCommand));
    }

    @Test
    public void toStringMethod() {
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(APPOINTMENT_ONE);
        CalendarWindow mockCalendarWindow = mock(CalendarWindow.class);
        addAppointmentCommand.setCalendarWindow(mockCalendarWindow);
        String expected = AddAppointmentCommand.class.getCanonicalName()
                + "{toAdd=" + APPOINTMENT_ONE + "}";
        assertEquals(expected, addAppointmentCommand.toString());
    }


    /**
     * A Model stub that contains a single appointment.
     */
    private class ModelStubWithAppointment extends ModelStub {

        final ObservableList<Appointment> appointmentAdded = FXCollections.observableArrayList();
        final FilteredList<Appointment> appointments = new FilteredList<>(appointmentAdded);
        final FilteredList<Dentist> dentists = new FilteredList<>(FXCollections.observableArrayList(
                new DentistBuilder()
                        .withName("Alice Pauline")
                        .withAddress("123, Jurong West Ave 6, #08-111")
                        .withEmail("alice@example.com")
                        .withPhone("94351253")
                        .withSpecialization("Orthodontics")
                        .withYoe("5")
                        .withTags("Professional")
                        .withDentistId("1")
                        .build()
        ));
        private ObservableList<Treatment> treatmentObservableList = FXCollections.observableArrayList(BRACES);

        final FilteredList<Treatment> treatments = new FilteredList<>(treatmentObservableList);

        private ObservableList<Patient> patientObservableList = FXCollections.observableArrayList(
                new PatientBuilder()
                        .withName("Amy")
                        .withAddress("123, Jurong West Ave 6, #08-111")
                        .withEmail("amy@example.com")
                        .withPhone("94351253")
                        .withGender("F")
                        .withBirthdate("01-01-1998")
                        .withRemark("Peanut Allergy")
                        .withTreatmentName("Cleaning")
                        .withTags("friends")
                        .withPatientId("1")
                        .build()
        );
        final FilteredList<Patient> patients = new FilteredList<>(patientObservableList);

        private final Appointment appointment;

        ModelStubWithAppointment(Appointment appointment) {
            requireNonNull(appointment);
            this.appointment = appointment;
            appointmentAdded.add(appointment);
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return this.appointment.isSameAppointment(appointment);
        }

        @Override
        public void addAppointment(Appointment appointment) {
            requireNonNull(appointment);
            appointmentAdded.add(appointment);
        }

        @Override
        public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
            requireNonNull(predicate);
            appointments.setPredicate(predicate);
        }

        @Override
        public FilteredList<Appointment> getFilteredAppointmentList() {
            return this.appointments;

        }
        @Override
        public void updateFilteredTreatmentList(Predicate<Treatment> predicate) {
            requireNonNull(predicate);
            treatments.setPredicate(predicate);
        }

        @Override
        public FilteredList<Treatment> getFilteredTreatmentList() {
            return this.treatments;
        }

        @Override
        public void updateFilteredDentistList(Predicate<Dentist> predicate) {
            requireNonNull(predicate);
            dentists.setPredicate(predicate);
        }

        @Override
        public FilteredList<Dentist> getFilteredDentistList() {
            return this.dentists;
        }

        @Override
        public Dentist getDentistById(long dentistId) {
            requireNonNull(dentistId);
            ObservableList<Dentist> filteredDentist = getFilteredDentistList();

            for (Dentist dentist : filteredDentist) {
                if (dentist.getId() == dentistId) {
                    return dentist;
                }
            }
            return null;
        }

        @Override
        public void updateFilteredPatientList(Predicate<Patient> predicate) {
            requireNonNull(predicate);
            patients.setPredicate(predicate);
        }

        @Override
        public FilteredList<Patient> getFilteredPatientList() {
            return this.patients;
        }

        @Override
        public Patient getPatientById(long patientId) {
            requireNonNull(patientId);
            ObservableList<Patient> filteredPatients = getFilteredPatientList();

            for (Patient patient : filteredPatients) {
                if (patient.getId() == patientId) {
                    return patient;
                }
            }
            return null;
        }

    }
    private class ModelStubAcceptingAppointmentAdded extends ModelStub {

        final ArrayList<Appointment> appointmentAdded = new ArrayList<>();
        final FilteredList<Treatment> treatments = new FilteredList<>(FXCollections.observableArrayList(BRACES));
        final FilteredList<Patient> patients = new FilteredList<>(FXCollections.observableArrayList(
                new PatientBuilder()
                        .withName("Amy")
                        .withAddress("123, Jurong West Ave 6, #08-111")
                        .withEmail("amy@example.com")
                        .withPhone("94351253")
                        .withGender("F")
                        .withBirthdate("01-01-1998")
                        .withRemark("Peanut Allergy")
                        .withTreatmentName("Cleaning")
                        .withTags("friends")
                        .withPatientId("1")
                        .build()
        ));

        final FilteredList<Dentist> dentists = new FilteredList<>(FXCollections.observableArrayList(
                new DentistBuilder()
                        .withName("Alice Pauline")
                        .withAddress("123, Jurong West Ave 6, #08-111")
                        .withEmail("alice@example.com")
                        .withPhone("94351253")
                        .withSpecialization("Orthodontics")
                        .withYoe("5")
                        .withTags("Professional")
                        .withDentistId("1")
                        .build()
        ));

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return appointmentAdded.stream().anyMatch(appointment::isSameAppointment);
        }

        @Override
        public void addAppointment(Appointment appointment) {
            requireNonNull(appointment);
            appointmentAdded.add(appointment);
        }

        @Override
        public void updateFilteredTreatmentList(Predicate<Treatment> predicate) {
            requireNonNull(predicate);
            treatments.setPredicate(predicate);
        }

        @Override
        public FilteredList<Treatment> getFilteredTreatmentList() {
            return this.treatments;
        }

        @Override
        public void updateFilteredDentistList(Predicate<Dentist> predicate) {
            requireNonNull(predicate);
            dentists.setPredicate(predicate);
        }

        @Override
        public FilteredList<Dentist> getFilteredDentistList() {
            return this.dentists;
        }

        @Override
        public Dentist getDentistById(long dentistId) {
            requireNonNull(dentistId);
            ObservableList<Dentist> filteredDentist = getFilteredDentistList();

            for (Dentist dentist : filteredDentist) {
                if (dentist.getId() == dentistId) {
                    return dentist;
                }
            }
            return null;
        }

        @Override
        public void updateFilteredPatientList(Predicate<Patient> predicate) {
            requireNonNull(predicate);
            patients.setPredicate(predicate);
        }

        @Override
        public FilteredList<Patient> getFilteredPatientList() {
            return this.patients;
        }

        @Override
        public Patient getPatientById(long patientId) {
            requireNonNull(patientId);
            ObservableList<Patient> filteredPatients = getFilteredPatientList();

            for (Patient patient : filteredPatients) {
                if (patient.getId() == patientId) {
                    return patient;
                }
            }
            return null;
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
