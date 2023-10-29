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
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private static final int UNDOS_ALLOWED = 5;
    private AddressBook addressBook;
    private final ArrayList<AddressBook> undoList;
    private final ArrayList<AddressBook> redoList;
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
        this.undoList = new ArrayList<>();
        this.redoList = new ArrayList<>();
        filteredDoctors = new FilteredList<>(this.addressBook.getDoctorList());
        filteredPatients = new FilteredList<>(this.addressBook.getPatientList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    private void updateBackup() {
        if (undoList.size() == UNDOS_ALLOWED) {
            undoList.remove(0);
        }
        undoList.add(new AddressBook(addressBook));
        redoList.clear();
    }

    @Override
    public void undo() throws CommandException {
        if (!undoList.isEmpty()) {
            redoList.add(new AddressBook(addressBook));
            addressBook.resetData(undoList.remove(undoList.size() - 1));
        } else {
            throw new CommandException(UndoCommand.MESSAGE_EMPTY);
        }
    }

    @Override
    public void redo() throws CommandException {
        if (!redoList.isEmpty()) {
            undoList.add(new AddressBook(addressBook));
            addressBook.resetData(redoList.remove(redoList.size() - 1));
        } else {
            throw new CommandException(RedoCommand.MESSAGE_EMPTY);
        }
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
        } else if (person instanceof Doctor) {
            return addressBook.hasDoctor((Doctor) person);
        } else {
            return false;
        }
    }
//
//    /**
//     * Returns true if a patient with the same ic as {@code patientIc} exists in the address book.
//     *
//     * @param patientIc The ic of patient to check.
//     * @return true if there is a patient with patientIc in the address book
//     */
//    public boolean hasPatientIc(Ic patientIc) {
//        requireNonNull(patientIc);
//        return addressBook.hasPatientIc(patientIc);
//    }
//
//    /**
//     * Returns true if a doctor with the same ic as {@code doctorIc} exists in the address book.
//     *
//     * @param doctorIc The ic of doctor to check.
//     * @return true if there is a patient with doctorIc in the address book
//     */
//    public boolean hasDoctorIc(Ic doctorIc) {
//        requireNonNull(doctorIc);
//        return addressBook.hasDoctorIc(doctorIc);
//    }

    @Override
    public void deletePerson(Person target) {
        if (target instanceof Patient) {
            updateBackup();
            addressBook.removePatient((Patient) target);
        } else if (target instanceof Doctor) {
            updateBackup();
            addressBook.removeDoctor((Doctor) target);
        }

    }

    @Override
    public void addPerson(Person person) {
        if (person instanceof Patient) {
            updateBackup();
            addressBook.addPatient((Patient) person);
        } else if (person instanceof Doctor) {
            updateBackup();
            addressBook.addDoctor((Doctor) person);
        }
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        if (target instanceof Patient && editedPerson instanceof Patient) {
            updateBackup();
            addressBook.setPatient((Patient) target, (Patient) editedPerson);
        } else if (target instanceof Doctor && editedPerson instanceof Doctor) {
            updateBackup();
            addressBook.setDoctor((Doctor) target, (Doctor) editedPerson);
        }
    }

//    @Override
//    public void addApointment(Appointment appointment) {
//        requireNonNull(appointment);
//        updateBackup();
//        addressBook.addAppointment(appointment);
//    }

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
