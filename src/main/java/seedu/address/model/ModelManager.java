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
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ManageHr manageHr;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given manageHr and userPrefs.
     */
    public ModelManager(ReadOnlyManageHr manageHr, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(manageHr, userPrefs);

        logger.fine("Initializing with address book: " + manageHr + " and user prefs " + userPrefs);

        this.manageHr = new ManageHr(manageHr);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.manageHr.getPersonList());
    }

    public ModelManager() {
        this(new ManageHr(), new UserPrefs());
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
    public Path getManageHrFilePath() {
        return userPrefs.getManageHrFilePath();
    }

    @Override
    public void setManageHrFilePath(Path manageHrFilePath) {
        requireNonNull(manageHrFilePath);
        userPrefs.setManageHrFilePath(manageHrFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setManageHr(ReadOnlyManageHr manageHr) {
        this.manageHr.resetData(manageHr);
    }

    @Override
    public ReadOnlyManageHr getManageHr() {
        return manageHr;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return manageHr.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        manageHr.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        manageHr.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        manageHr.setPerson(target, editedPerson);
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
        return manageHr.equals(otherModelManager.manageHr)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons);
    }

}
