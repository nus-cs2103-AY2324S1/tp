package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.doctor.Doctor;
import seedu.address.model.person.Person;
import seedu.address.model.timeslots.Timeslot;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ClinicAssistant clinicAssistant;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Doctor> filteredDoctor;
    private final FilteredList<Appointment> filteredAppointments;
    private FilteredList<Timeslot> filteredTimeSlots;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyClinicAssistant addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);
        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.clinicAssistant = new ClinicAssistant(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.clinicAssistant.getPersonList());
        filteredAppointments = new FilteredList<>(this.clinicAssistant.getAppointmentList());
        filteredDoctor = new FilteredList<>(this.clinicAssistant.getDoctorList());
        filteredTimeSlots = new FilteredList<>(this.clinicAssistant.getTimeSlotList());
    }

    public ModelManager() {
        this(new ClinicAssistant(), new UserPrefs());
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
    public void setAddressBook(ReadOnlyClinicAssistant addressBook) {
        this.clinicAssistant.resetData(addressBook);
    }

    @Override
    public ReadOnlyClinicAssistant getAddressBook() {
        return clinicAssistant;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return clinicAssistant.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        clinicAssistant.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        clinicAssistant.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        clinicAssistant.setPerson(target, editedPerson);
    }

    @Override
    public void addAppointment(Appointment appointment) {
        clinicAssistant.addAppointment(appointment);
        updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPTS);
    }

    @Override
    public void deleteAppointment(Appointment appointment) {
        clinicAssistant.deleteAppointment(appointment);
        updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPTS);
    }

    @Override
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);

        clinicAssistant.setAppointment(target, editedAppointment);
    }

    @Override
    public void editedPersonAppointments(ArrayList<Appointment> oldAppointments, ArrayList<Appointment> toReplace) {
        requireAllNonNull(oldAppointments, toReplace);
        clinicAssistant.editedPersonAppointments(oldAppointments, toReplace);
    }
    @Override
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return clinicAssistant.hasAppointment(appointment);
    }

    @Override
    public boolean hasDoctor(Doctor doctor) {
        requireNonNull(doctor);
        return clinicAssistant.hasDoctor(doctor);
    }

    @Override
    public void deleteDoctor(Doctor target) {
        clinicAssistant.removeDoctor(target);
    }

    @Override
    public void addDoctor(Doctor doctor) {
        clinicAssistant.addDoctor(doctor);
        updateFilteredDoctorList(PREDICATE_SHOW_ALL_DOCTORS);
    }

    @Override
    public void setDoctor(Doctor target, Doctor editedDoctor) {
        requireAllNonNull(target, editedDoctor);

        clinicAssistant.setDoctor(target, editedDoctor);
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

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Filtered Appointments List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Appointment} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return filteredAppointments;
    }

    @Override
    public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
        requireNonNull(predicate);
        filteredAppointments.setPredicate(predicate);
    }

    //=========== Filtered Doctors List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Appointment} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Doctor> getFilteredDoctorList() {
        return filteredDoctor;
    }

    @Override
    public void updateFilteredDoctorList(Predicate<Doctor> predicate) {
        requireNonNull(predicate);
        filteredDoctor.setPredicate(predicate);
    }

    @Override
    public void addAvailableTimeSlot(Timeslot timeslot) {
        clinicAssistant.addAvailableTimeSlot(timeslot);
        updateFilteredAvailableTimeslot(PREDICATE_SHOW_ALL_TIMESLOTS);
    }
    @Override
    public void removeAvailableTimeSlot(Timeslot timeslot) {
        clinicAssistant.removeAvailableTimeSlot(timeslot);
        updateFilteredAvailableTimeslot(PREDICATE_SHOW_ALL_TIMESLOTS);
    }

    /**
     * Resets the list of available timeslots
     */
    @Override
    public void resetAvailableTimeSlot() {
        clinicAssistant.resetTimeslots();
    }

    @Override
    public void updateFilteredAvailableTimeslot(Predicate<Timeslot> predicate) {
        requireNonNull(predicate);
        filteredTimeSlots.setPredicate(predicate);
    }
    @Override
    public ObservableList<Timeslot> getAvailableTimeSlotList() {
        return this.clinicAssistant.getTimeSlotList();
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
        return clinicAssistant.equals(otherModelManager.clinicAssistant)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons)
                && filteredAppointments.equals(otherModelManager.filteredAppointments);
    }

}
