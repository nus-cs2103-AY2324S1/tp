package seedu.address.model;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Name;
import seedu.address.model.person.SortIn;
import seedu.address.model.person.Student;
import seedu.address.model.person.Visual;
import seedu.address.ui.BarChartWindow;
import seedu.address.ui.TableWindow;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    protected static BarChartWindow barChartWindow = null;
    protected static TableWindow tableWindow = null;
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private FilteredList<Student> filteredStudents;


    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.addressBook.getPersonList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    // =========== UserPrefs
    // ==================================================================================

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

    // =========== AddressBook
    // ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Student student) {
        requireNonNull(student);
        return addressBook.hasPerson(student);
    }

    @Override
    public void deletePerson(Student target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Student student) {
        addressBook.addPerson(student);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        addressBook.setPerson(target, editedStudent);
    }

    // =========== Filtered Student List Accessors
    // =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the
     * internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Student> getFilteredPersonList() {
        return filteredStudents;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    @Override
    public void updateSortedPersonList(SortIn sequence) {
        requireNonNull(sequence);
        addressBook.sort(sequence);
    }

    public static void getTable(TableWindow table) {
        requireNonNull(table);
        tableWindow = table;
    }

    public static void getBarChart(BarChartWindow barChart) {
        requireNonNull(barChart);
        barChartWindow = barChart;
    }

    @Override
    public void export(Visual visual) throws Exception {
        requireNonNull(visual);
        if (visual.toString().equals("TABLE") && !isNull(tableWindow)) {
            tableWindow.exportAsPng();
        } else if (visual.toString().equals("BAR") && !isNull(barChartWindow)) {
            barChartWindow.exportAsPng();
        } else {
            throw new Exception();
        }
    }

    @Override
    public Optional<Student> getStudentFromFilteredPersonListByName(Name name) {
        Optional<Student> targetStudent = Optional.ofNullable(null);
        for (Student student : filteredStudents) {
            if (student.getName().equals(name)) {
                targetStudent = Optional.of(student);
                break;
            }
        }
        return targetStudent;
    }

    @Override
    public Optional<Student> getStudentFromFilteredPersonListByIndex(Index index) {
        int zerobasedIndex;
        if (index == null || (zerobasedIndex = index.getZeroBased()) < 0 || zerobasedIndex >= filteredStudents.size()) {
            return Optional.ofNullable(null);
        }
        return Optional.of(filteredStudents.get(zerobasedIndex));
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
                && filteredStudents.equals(otherModelManager.filteredStudents);
    }



}
