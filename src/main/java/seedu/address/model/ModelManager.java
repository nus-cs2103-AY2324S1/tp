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
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Doctor> filteredDoctors;
    private final FilteredList<Patient> filteredPatients;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredDoctors = new FilteredList<>(this.addressBook.getDoctorList());
        filteredPatients = new FilteredList<>(this.addressBook.getPatientList());
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
        if (person instanceof Patient) {
            return addressBook.hasPatient((Patient) person);
        } else {
            return false;
        }
    }

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the address book.
     *
     * @param patient
     */
    @Override
    public boolean hasPatient(Patient patient) {
        return false;
    }

    /**
     * Returns true if a doctor with the same identity as {@code doctor} exists in the address book.
     *
     * @param doctor
     */
    @Override
    public boolean hasDoctor(Doctor doctor) {
        return false;
    }

    @Override
    public void deletePerson(Person target) {
        if (target instanceof Patient) {
            addressBook.removePatient((Patient) target);
        }
    }

    /**
     * Deletes the given patient.
     * The person must exist in the address book.
     *
     * @param target
     */
    @Override
    public void deletePatient(Patient target) {

    }

    /**
     * Deletes the given doctor.
     * The doctor must exist in the address book.
     *
     * @param target
     */
    @Override
    public void deleteDoctor(Doctor target) {

    }

    @Override
    public void addPerson(Person person) {
        if (person instanceof Patient) {
            addressBook.addPatient((Patient) person);
        }
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    /**
     * Adds the given patient.
     * {@code patient} must not already exist in the address book.
     *
     * @param patient
     */
    @Override
    public void addPatient(Patient patient) {

    }

    /**
     * Adds the given doctor.
     * {@code doctor} must not already exist in the address book.
     *
     * @param doctor
     */
    @Override
    public void addDoctor(Doctor doctor) {

    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        if (target instanceof Patient && editedPerson instanceof Patient) {
            addressBook.setPatient((Patient) target, (Patient) editedPerson);
        }
    }

    /**
     * Replaces the given patient {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     *
     * @param target
     * @param editedPerson
     */
    @Override
    public void setPatient(Patient target, Patient editedPerson) {

    }

    /**
     * Replaces the given doctor {@code target} with {@code editedDoctor}.
     * {@code target} must exist in the address book.
     * The doctor identity of {@code editedDoctor} must not be the same as another existing person in the address book.
     *
     * @param target
     * @param editedDoctor
     */
    @Override
    public void setDoctor(Doctor target, Doctor editedDoctor) {

    }

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return null;
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Patient} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        return filteredPatients;
    }

    /**
     * Returns an unmodifiable view of the filtered doctor list
     */
    @Override
    public ObservableList<Doctor> getFilteredDoctorList() {
        return filteredDoctors;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPatients.setPredicate(predicate);
    }

    /**
     * Updates the filter of the filtered patient list to filter by the given {@code predicate}.
     *
     * @param predicate
     * @throws NullPointerException if {@code predicate} is null.
     */
    @Override
    public void updateFilteredPatientList(Predicate<Patient> predicate) {

    }

    @Override
    public void updateFilteredDoctorList(Predicate<Doctor> predicate) {
        requireNonNull(predicate);
        filteredDoctors.setPredicate(predicate);
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
                && filteredDoctors.equals(otherModelManager.filteredDoctors)
                && filteredPatients.equals(otherModelManager.filteredPatients);
    }

}
