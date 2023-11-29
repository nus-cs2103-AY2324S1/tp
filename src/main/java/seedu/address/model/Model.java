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
 * The API of the Model component.
 */
public interface Model {

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Patient> PREDICATE_SHOW_ALL_PATIENTS = unused -> true;
    Predicate<Dentist> PREDICATE_SHOW_ALL_DENTISTS = unused -> true;
    Predicate<Treatment> PREDICATE_SHOW_ALL_TREATMENTS = unused -> true;
    Predicate<Appointment> PREDICATE_SHOW_ALL_APPOINTMENTS = unused -> true;


    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in ToothTracker
     * address book.
     */
    boolean hasPerson(Person person);

    /**
     * Returns true if a patient with the same identity as {@code person} exists in the address
     * book.
     */
    boolean hasPatient(Patient person);

    /**
     * Returns true if a treatment with the same identity as {@code person} exists in the address
     * book.
     */
    boolean hasTreatment(Treatment treatment);

    boolean hasTreatmentName(TreatmentName treatmentName);

    /**
     * Returns true if a dentist with the same identity as {@code dentist} exists in ToothTracker
     * address book.
     */
    boolean hasDentist(Dentist dentist);

    boolean hasAppointment(Appointment appointment);

    Patient getPatientById(long patientId);

    Dentist getDentistById(long dentistId);

    Appointment getAppointmentById(long appointmentId);


    /**
     * returns the treatment by its name
     *
     * @param treatmentName name of the treatment to find
     * @return null if the treatment is not found. Otherwise, the corresponding Treatment object is returned
     */
    Treatment getTreatmentByName(String treatmentName);

    /**
     * Deletes the given person. The person must exist in the address book.
     */
    void deletePerson(Person target);

    void deletePatient(Patient patient);

    void deleteDentist(Dentist dentist);

    void deleteAppointment(Appointment appointment);

    void deleteTreatment(Treatment treatment);


    /**
     * Adds the given person. {@code person} must not already exist in ToothTracker address book.
     */
    void addPerson(Person person);

    /**
     * Adds the given Patient. {@code Patient} must not already exist in the address book.
     */
    void addPatient(Patient patient);

    /**
     * Adds the given dentist. {@code dentist} must not already exist in ToothTracker address book.
     */
    void addDentist(Dentist dentist);

    /**
     * Adds the given appointment.
     */
    void addAppointment(Appointment appointment);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}. {@code target} must exist
     * in the address book. The person identity of {@code editedPerson} must not be the same as
     * another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Replaces the given dentist {@code target} with {@code editedDentist}. {@code target} must
     * exist in the address book. The dentist identity of {@code editedDentist} must not be the same
     * as another existing dentist in the address book.
     */
    void setDentist(Dentist target, Dentist editedDentist);


    /**
     * Replaces the given patient {@code target} with {@code editedPatient}. {@code target} must
     * exist in the address book. The patient identity of {@code editedPatient} must not be the same
     * as another existing patient in the address book.
     */
    void setPatient(Patient target, Patient editedPatient);

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns an unmodifiable view of the filtered patient list
     */
    ObservableList<Patient> getFilteredPatientList();

    /**
     * Returns an unmodifiable view of the filtered dentist list
     */
    ObservableList<Dentist> getFilteredDentistList();

    ObservableList<Appointment> getFilteredAppointmentList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the filter of the filtered patient list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPatientList(Predicate<Patient> predicate);

    void updateFilteredPatientList(NameContainsKeywordsPredicate predicate);

    /**
     * Updates the filter of the filtered dentist list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredDentistList(Predicate<Dentist> predicate);

    void updateFilteredDentistList(NameContainsKeywordsPredicate predicate);

    /**
     * Adds the given Treatment. {@code Treatment} must not already exist in the address book.
     */
    void addTreatment(Treatment treatment);

    /**
     * Returns an unmodifiable view of the filtered treatment list
     */
    ObservableList<Treatment> getFilteredTreatmentList();

    /**
     * Updates the filter of the filtered treatment list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTreatmentList(Predicate<Treatment> predicate);

    /**
     * Updates the filter of the filtered Appointment list to filter by the given
     * {@code predicate}.
     *
     * @param appointmentPredicate The predicate to filter the list by.
     */
    void updateFilteredAppointmentList(Predicate<Appointment> appointmentPredicate);

}
