package seedu.staffsnap.model;

import static java.util.Objects.requireNonNull;
import static seedu.staffsnap.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.staffsnap.commons.core.GuiSettings;
import seedu.staffsnap.commons.core.LogsCenter;
import seedu.staffsnap.model.applicant.Applicant;
import seedu.staffsnap.model.applicant.Descriptor;

/**
 * Represents the in-memory model of the applicant book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ApplicantBook applicantBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Applicant> filteredApplicants;

    /**
     * Initializes a ModelManager with the given applicantBook and userPrefs.
     */
    public ModelManager(ReadOnlyApplicantBook applicantBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(applicantBook, userPrefs);

        logger.fine("Initializing with applicant book: " + applicantBook + " and user prefs " + userPrefs);

        this.applicantBook = new ApplicantBook(applicantBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredApplicants = new FilteredList<>(this.applicantBook.getApplicantList());
    }

    public ModelManager() {
        this(new ApplicantBook(), new UserPrefs());
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
    public Path getApplicantBookFilePath() {
        return userPrefs.getApplicantBookFilePath();
    }

    @Override
    public void setApplicantBookFilePath(Path applicantBookFilePath) {
        requireNonNull(applicantBookFilePath);
        userPrefs.setApplicantBookFilePath(applicantBookFilePath);
    }

    //=========== ApplicantBook ================================================================================

    @Override
    public void setApplicantBook(ReadOnlyApplicantBook applicantBook) {
        this.applicantBook.resetData(applicantBook);
    }

    @Override
    public ReadOnlyApplicantBook getApplicantBook() {
        return applicantBook;
    }

    @Override
    public boolean hasApplicant(Applicant applicant) {
        requireNonNull(applicant);
        return applicantBook.hasApplicant(applicant);
    }

    @Override
    public boolean isDuplicateApplicant(Applicant applicant) {
        requireNonNull(applicant);
        return applicantBook.isDuplicateApplicant(applicant);
    }

    @Override
    public void deleteApplicant(Applicant target) {
        applicantBook.removeApplicant(target);
    }

    @Override
    public void addApplicant(Applicant applicant) {
        applicantBook.addApplicant(applicant);
        updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
    }

    @Override
    public void setApplicant(Applicant target, Applicant editedApplicant) {
        requireAllNonNull(target, editedApplicant);

        applicantBook.setApplicant(target, editedApplicant);
    }

    //=========== Filtered Applicant List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Applicant} backed by the internal list of
     * {@code versionedApplicantBook}
     */
    @Override
    public ObservableList<Applicant> getFilteredApplicantList() {
        return new SortedList<>(filteredApplicants).sorted();
    }

    @Override
    public void updateFilteredApplicantList(Predicate<Applicant> predicate) {
        requireNonNull(predicate);
        filteredApplicants.setPredicate(predicate);
    }

    @Override
    public void updateSortedApplicantList(Descriptor descriptor) {
        Applicant.setDescriptor(descriptor);
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
        return applicantBook.equals(otherModelManager.applicantBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredApplicants.equals(otherModelManager.filteredApplicants);
    }

}
