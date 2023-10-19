package networkbook.model;

import static java.util.Objects.requireNonNull;
import static networkbook.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import networkbook.commons.core.GuiSettings;
import networkbook.commons.core.LogsCenter;
import networkbook.model.person.Person;

/**
 * Represents the in-memory model of the network book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final NetworkBook networkBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final SortedList<Person> filteredSortedPersons;

    /**
     * Initializes a ModelManager with the given networkBook and userPrefs.
     */
    public ModelManager(ReadOnlyNetworkBook networkBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(networkBook, userPrefs);

        logger.fine("Initializing with network book: " + networkBook + " and user prefs " + userPrefs);

        this.networkBook = new NetworkBook(networkBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.networkBook.getPersonList());
        filteredSortedPersons = new SortedList<>(filteredPersons, null);
    }

    public ModelManager() {
        this(new NetworkBook(), new UserPrefs());
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
    public Path getNetworkBookFilePath() {
        return userPrefs.getNetworkBookFilePath();
    }

    @Override
    public void setNetworkBookFilePath(Path networkBookFilePath) {
        requireNonNull(networkBookFilePath);
        userPrefs.setNetworkBookFilePath(networkBookFilePath);
    }

    //=========== NetworkBook ================================================================================

    @Override
    public void setNetworkBook(ReadOnlyNetworkBook networkBook) {
        this.networkBook.resetData(networkBook);
    }

    @Override
    public ReadOnlyNetworkBook getNetworkBook() {
        return networkBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return networkBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        networkBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        networkBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setItem(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        networkBook.setItem(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedNetworkBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredSortedPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void updateSortedPersonList(Comparator<Person> comparator) {
        requireNonNull(comparator);
        filteredSortedPersons.setComparator(comparator);
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
        return networkBook.equals(otherModelManager.networkBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons)
                && filteredSortedPersons.equals(otherModelManager.filteredSortedPersons);
    }

}
