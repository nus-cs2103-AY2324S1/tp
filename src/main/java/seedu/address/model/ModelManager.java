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
import seedu.address.model.internship.Internship;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final InternshipBook internshipBook;
    private final UserPrefs userPrefs;

    private final FilteredList<Internship> filteredInternships;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyInternshipBook internshipBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(internshipBook, userPrefs);

        logger.fine("Initializing with address book: " + internshipBook + " and user prefs " + userPrefs);

        this.internshipBook = new InternshipBook(internshipBook);
        this.userPrefs = new UserPrefs(userPrefs);

        this.filteredInternships = new FilteredList<>(this.internshipBook.getInternshipList());
    }

    public ModelManager() {
        this(new InternshipBook(), new UserPrefs());
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
    public Path getInternshipBookFilePath() {
        return userPrefs.getInternshipFilePath();
    }

    @Override
    public void setInternshipBookFilePath(Path internshipBookFilePath) {
        requireNonNull(internshipBookFilePath);
        userPrefs.setInternshipFilePath(internshipBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setInternshipBook(ReadOnlyInternshipBook internshipBook) {
        this.internshipBook.resetData(internshipBook);
    }

    @Override
    public ReadOnlyInternshipBook getInternshipBook() {
        return internshipBook;
    }


    @Override
    public boolean hasInternship(Internship internship) {
        requireNonNull(internship);
        return this.internshipBook.hasInternship(internship);
    }

    @Override
    public void deleteInternship(Internship target) {
        internshipBook.removeInternship(target);
    }


    @Override
    public void createInternship(Internship internship) {
        internshipBook.createInternship(internship);
        updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
    }

    @Override
    public void setInternship(Internship target, Internship editedInternship) {
        requireAllNonNull(target, editedInternship);

        internshipBook.setInternship(target, editedInternship);
    }


    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Internship> getFilteredInternshipList() {
        return filteredInternships;

    }

    @Override
    public void updateFilteredInternshipList(Predicate<Internship> predicate) {
        requireNonNull(predicate);
        this.filteredInternships.setPredicate(predicate);
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
        return internshipBook.equals(otherModelManager.internshipBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredInternships.equals(otherModelManager.filteredInternships);
    }

}
