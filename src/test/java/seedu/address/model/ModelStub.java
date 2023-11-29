package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.appointments.Appointment;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.dentist.Dentist;
import seedu.address.model.person.patients.Patient;
import seedu.address.model.treatment.Treatment;
import seedu.address.model.treatment.TreatmentName;


/**
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {

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
    public void deletePerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deletePatient(Patient patient) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteDentist(Dentist dentist) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteAppointment(Appointment appointment) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setDentist(Dentist target, Dentist editedDentist) {
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
    public void updateFilteredPatientList(Predicate<Patient> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredPatientList(NameContainsKeywordsPredicate predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredDentistList(Predicate<Dentist> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredDentistList(NameContainsKeywordsPredicate predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Dentist> getFilteredDentistList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addPatient(Patient patient) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addDentist(Dentist dentist) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addAppointment(Appointment appointment) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasPatient(Patient patient) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasDentist(Dentist dentist) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasAppointment(Appointment appointment) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Patient getPatientById(long patientId) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Dentist getDentistById(long dentistId) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Appointment getAppointmentById(long appointmentId) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredTreatmentList(Predicate<Treatment> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredAppointmentList(Predicate<Appointment> appointmentPredicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Treatment> getFilteredTreatmentList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addTreatment(Treatment treatment) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasTreatment(Treatment treatment) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasTreatmentName(TreatmentName treatmentName) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteTreatment(Treatment treatment) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Treatment getTreatmentByName(String treatmentName) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPatient(Patient target, Patient editedPatient) {
        throw new AssertionError("This method should not be called.");
    }


}
