package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointments.Appointment;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.dentist.Dentist;
import seedu.address.model.person.patients.Patient;
import seedu.address.model.treatment.Treatment;
import seedu.address.model.treatment.TreatmentName;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Patient> filteredPatients;
    private final FilteredList<Dentist> filteredDentists;
    private final FilteredList<Appointment> filteredAppointments;
    private final FilteredList<Treatment> filteredTreatments;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine(
            "Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredPatients = new FilteredList<>(this.addressBook.getPatientList());
        filteredDentists = new FilteredList<>(this.addressBook.getDentistList());
        filteredAppointments = new FilteredList<>(this.addressBook.getAppointmentList());
        filteredTreatments = new FilteredList<>(this.addressBook.getTreatmentList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return addressBook.hasPatient(patient);
    }

    @Override
    public boolean hasDentist(Dentist dentist) {
        requireNonNull(dentist);
        return addressBook.hasDentist(dentist);
    }

    @Override
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return addressBook.hasAppointment(appointment);
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
    public Appointment getAppointmentById(long appointmentId) {
        requireNonNull(appointmentId);
        Predicate<Appointment> appointmentPredicate = appointment -> true;
        updateFilteredAppointmentList(appointmentPredicate);
        ObservableList<Appointment> filteredAppointment = getFilteredAppointmentList();

        for (Appointment appointment : filteredAppointment) {
            if (appointment.getId() == appointmentId) {
                return appointment;
            }
        }
        return null;
    }


    @Override
    public Treatment getTreatmentByName(String treatmentName) {
        requireNonNull(treatmentName);
        ObservableList<Treatment> filteredTreatment = getFilteredTreatmentList();

        for (Treatment treatment : filteredTreatment) {
            String trimmedInner = treatment.getName().toString().trim();
            String trimmedTreatmentName = treatmentName.trim();
            if (trimmedTreatmentName.equals(trimmedInner)) {
                return treatment;
            }
        }
        return null;
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void deletePatient(Patient patient) {
        addressBook.removePatient(patient);
    }

    @Override
    public void deleteDentist(Dentist dentist) {
        addressBook.removeDentist(dentist);
    }

    @Override
    public void deleteAppointment(Appointment appointment) {
        addressBook.removeAppointment(appointment);
    }

    @Override
    public void deleteTreatment(Treatment treatment) {
        addressBook.removeTreatment(treatment);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addPatient(Patient patient) {
        addressBook.addPatient(patient);
    }

    @Override
    public void addDentist(Dentist dentist) {
        addressBook.addDentist(dentist);
    }

    @Override
    public void addAppointment(Appointment appointment) {
        addressBook.addAppointment(appointment);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public void setDentist(Dentist target, Dentist editedDentist) {
        requireAllNonNull(target, editedDentist);

        addressBook.setDentist(target, editedDentist);
    }

    @Override
    public void setPatient(Patient target, Patient editedPatient) {
        requireAllNonNull(target, editedPatient);

        addressBook.setPatient(target, editedPatient);
    }

    @Override
    public void addTreatment(Treatment treatment) {
        addressBook.addTreatment(treatment);
    }

    @Override
    public ObservableList<Treatment> getFilteredTreatmentList() {
        return filteredTreatments;
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Patient} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        return filteredPatients;
    }

    @Override
    public ObservableList<Dentist> getFilteredDentistList() {
        return filteredDentists;
    }

    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return filteredAppointments;
    }


    @Override
    public void updateFilteredTreatmentList(Predicate<Treatment> predicate) {
        requireNonNull(predicate);
        filteredTreatments.setPredicate(predicate);
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void updateFilteredPatientList(Predicate<Patient> predicate) {
        requireNonNull(predicate);
        filteredPatients.setPredicate(predicate);
    }

    @Override
    public void updateFilteredPatientList(NameContainsKeywordsPredicate predicate) {
        requireNonNull(predicate);
        filteredPatients.setPredicate(predicate);
    }

    @Override
    public void updateFilteredDentistList(Predicate<Dentist> predicate) {
        requireNonNull(predicate);
        filteredDentists.setPredicate(predicate);
    }

    @Override
    public void updateFilteredDentistList(NameContainsKeywordsPredicate predicate) {
        requireNonNull(predicate);
        filteredDentists.setPredicate(predicate);
    }

    @Override
    public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
        requireNonNull(predicate);
        filteredAppointments.setPredicate(predicate);
    }

    @Override
    public boolean hasTreatment(Treatment treatment) {
        requireNonNull(treatment);
        return addressBook.hasTreatment(treatment);
    }

    @Override
    public boolean hasTreatmentName(TreatmentName treatmentName) {
        requireNonNull(treatmentName);
        return addressBook.hasTreatmentName(treatmentName);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return addressBook.equals(otherModelManager.addressBook)
            && userPrefs.equals(otherModelManager.userPrefs)
            && filteredPersons.equals(otherModelManager.filteredPersons)
            && filteredPatients.equals(otherModelManager.filteredPatients)
            && filteredTreatments.equals(otherModelManager.filteredTreatments);
    }

}
