package seedu.staffsnap.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.staffsnap.commons.core.GuiSettings;
import seedu.staffsnap.model.applicant.Applicant;
import seedu.staffsnap.model.applicant.Descriptor;
/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Applicant> PREDICATE_SHOW_ALL_APPLICANTS = unused -> true;

    /** {@code Predicate} that always evaluate to false */
    Predicate<Applicant> PREDICATE_HIDE_ALL_APPLICANTS = unused -> false;

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
     * Returns the user prefs' applicant book file path.
     */
    Path getApplicantBookFilePath();

    /**
     * Sets the user prefs' applicant book file path.
     */
    void setApplicantBookFilePath(Path applicantBookFilePath);

    /**
     * Replaces applicant book data with the data in {@code applicantBook}.
     */
    void setApplicantBook(ReadOnlyApplicantBook applicantBook);

    /** Returns the ApplicantBook */
    ReadOnlyApplicantBook getApplicantBook();

    /**
     * Returns true if an applicant with the same identity as {@code applicant} exists in the applicant book.
     */
    boolean hasApplicant(Applicant applicant);

    /**
     * Returns true if an applicant with the same identity as {@code applicant} exists in the applicant book
     * and they are not the same applicant.
     */
    boolean isDuplicateApplicant(Applicant applicant);

    /**
     * Deletes the given applicant.
     * The applicant must exist in the applicant book.
     */
    void deleteApplicant(Applicant target);

    /**
     * Adds the given applicant.
     * {@code applicant} must not already exist in the applicant book.
     */
    void addApplicant(Applicant applicant);

    /**
     * Replaces the given applicant {@code target} with {@code editedApplicant}.
     * {@code target} must exist in the applicant book.
     * The applicant identity of {@code editedApplicant} must not be the same as another existing applicant in the
     * applicant book.
     */
    void setApplicant(Applicant target, Applicant editedApplicant);

    /** Returns an unmodifiable view of the filtered applicant list */
    ObservableList<Applicant> getFilteredApplicantList();

    /**
     * Updates the filter of the filtered applicant list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredApplicantList(Predicate<Applicant> predicate);
    /**
     * Updates the Descriptor for sorting Applicants.
     */
    void updateSortedApplicantList(Descriptor descriptor);
}
