package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.ScheduleItem;
import seedu.address.model.appointment.SortByAppointmentDateComparator;
import seedu.address.model.person.Person;
import seedu.address.model.person.gatheremail.GatherEmailPrompt;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private static final SortByAppointmentDateComparator appointmentComparator = new SortByAppointmentDateComparator();

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private SortedList<Person> sortedPersons;
    private ObservableList<Appointment> observableAppointments;
    private SortedList<Appointment> sortedAppointments;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        sortedPersons = new SortedList<>(this.addressBook.getPersonList());
        filteredPersons = new FilteredList<>(sortedPersons);
        observableAppointments = FXCollections.observableArrayList();
        sortedAppointments = new SortedList<>(observableAppointments,
                                              appointmentComparator);
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
        setAppointmentList();
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
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
        setAppointmentList();
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        addressBook.setPerson(target, editedPerson);
        setAppointmentList();
    }

    @Override
    public String gatherEmails(GatherEmailPrompt prompt) {
        return addressBook.gatherEmails(prompt);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        setAppointmentList();
        return filteredPersons;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public SortedList<Appointment> getAppointmentList() {
        setAppointmentList();
        return sortedAppointments;
    }

    /**
     * Sets the appointment list. This method is called every time a command is being executed.
     */
    public void setAppointmentList() {
        observableAppointments.clear();
        filteredPersons.forEach(person -> addToAppointmentListIfPresent(person));
    }


    /**
     * Adds ScheduleItem object from person to the appointment list if ScheduleItem object
     * is an instance of Appointment and not an instance of NullAppointment.
     * @param person Person object that is being examined.
     */
    public void addToAppointmentListIfPresent(Person person) {
        ScheduleItem scheduleItem = person.getAppointment();
        if (scheduleItem instanceof Appointment) {
            Appointment appointment = (Appointment) scheduleItem;
            appointment.setPerson(person);
            observableAppointments.add(appointment);
        }
    }

    @Override
    public void clearAppointments(LocalDate date) {
        addressBook.clearAppointments(date);
        setAppointmentList();
    }

    @Override
    public boolean hasAppointmentWithDate(LocalDate date) {
        return addressBook.hasAppointmentWithDate(date);
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
        setAppointmentList();
    }


    @Override
    public void sortFilteredPersonList(Comparator<Person> comparator) {
        requireNonNull(comparator);
        sortedPersons.setComparator(comparator);
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
                && filteredPersons.equals(otherModelManager.filteredPersons);
    }

}
